package com.example.saudeapiback.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class CepServices {

    private static final double R = 6371.0; // Raio da Terra em KM
    private static final String API_KEY = "f3853bcd03d04d3cbd13ef68ef5f14ee";

    // Função para obter as coordenadas de um CEP a partir da API OpenCage
    public double[] getCoordinatesFromCep(String cep) throws IOException, InterruptedException {
        String url = "https://api.opencagedata.com/geocode/v1/json?q=" + cep + ",Brazil&key=" + API_KEY;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Processar a resposta JSON
            String responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            if (rootNode.path("results").isArray() && rootNode.path("results").size() > 0) {
                double latitude = rootNode.path("results").get(0).path("geometry").path("lat").asDouble();
                double longitude = rootNode.path("results").get(0).path("geometry").path("lng").asDouble();
                return new double[]{latitude, longitude};
            } else {
                throw new RuntimeException("Coordenadas não encontradas para o CEP: " + cep);
            }
        } else {
            throw new RuntimeException("Erro na requisição para o CEP: " + cep);
        }
    }

    // Função para calcular a distância entre duas coordenadas usando a fórmula de Haversine
    public double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distância em km
    }

    // Função para carregar os CEPs e suas coordenadas de um arquivo local
    public Map<String, double[]> loadCeps(String filePath) throws IOException {
        Map<String, double[]> ceps = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" - ");
            if (parts.length == 3) {
                String cep = parts[0].trim();
                double latitude = Double.parseDouble(parts[1].trim());
                double longitude = Double.parseDouble(parts[2].trim());
                ceps.put(cep, new double[]{latitude, longitude});
            }
        }
        reader.close();
        return ceps;
    }

    // Função para encontrar CEPs dentro de uma distância especificada
    public List<String> findCepsWithinDistance(String referenceCep, double maxDistanceKm, String filePath) throws IOException, InterruptedException {
        Map<String, double[]> ceps = loadCeps(filePath);
        double[] referenceCoords = getCoordinatesFromCep(referenceCep);

        List<String> filteredCeps = new ArrayList<>();
        double refLat = referenceCoords[0];
        double refLon = referenceCoords[1];

        for (Map.Entry<String, double[]> entry : ceps.entrySet()) {
            String cep = entry.getKey();
            double[] coords = entry.getValue();
            double distance = haversine(refLat, refLon, coords[0], coords[1]);
            if (distance <= maxDistanceKm) {
                filteredCeps.add(cep);
            }
        }
        return filteredCeps;
    }
}
