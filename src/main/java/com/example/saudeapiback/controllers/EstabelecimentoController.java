package com.example.saudeapiback.controllers;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.saudeapiback.dtos.CityEstablishmentParams;
import com.example.saudeapiback.dtos.EstabelecimentoDTO;
import com.example.saudeapiback.dtos.EstablishmentParams;
import com.example.saudeapiback.service.EstabelecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/establishment")
@Tag(name = "Estabelecimentos", description = "Gerenciamento de estabelecimentos de saúde")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Operation(
            summary = "Buscar estabelecimentos por cidade",
            description = "Este endpoint retorna uma lista de estabelecimentos filtrados pelo código de município e, opcionalmente, o tipo de unidade.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Parâmetros para a busca de estabelecimentos",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CityEstablishmentParams.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, estabelecimentos retornados"),
            @ApiResponse(responseCode = "404", description = "Nenhum estabelecimento encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição, verifique os parâmetros enviados")
    })
    @GetMapping("/get_establishments_by_city")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstablishmentsByCity(
            @RequestBody Map<String, Integer> params) {
        List<EstabelecimentoDTO> estabelecimentoDTOs = estabelecimentoService.getEstabelecimentos(params);
        if (estabelecimentoDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estabelecimentoDTOs);
    }
    @Operation(
            summary = "Buscar estabelecimentos por CEP e distância",
            description = "Este endpoint retorna uma lista de estabelecimentos próximos ao CEP de referência informado, com base na distância máxima e tipo de estabelecimento fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso, estabelecimentos retornados"),
            @ApiResponse(responseCode = "404", description = "Nenhum estabelecimento encontrado dentro da distância informada"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição, verifique os parâmetros enviados")
    })
    @GetMapping("/get_establishments_by_cep")
    public ResponseEntity<List<EstabelecimentoDTO>> getEstablishmentsByCep(
            @RequestBody EstablishmentParams params) {
        try {
            // Convertemos o objeto EstablishmentParams para Map<String, Object>
            Map<String, Object> paramsMap = Map.of(
                    "cep", params.getCep(),
                    "distance", params.getDistance(),
                    "tipo_estabelecimento", params.getTipo_estabelecimento()
            );

            List<EstabelecimentoDTO> estabelecimentoDTOs = estabelecimentoService.getEstablishmentsByCep(paramsMap);
            if (estabelecimentoDTOs.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(estabelecimentoDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}