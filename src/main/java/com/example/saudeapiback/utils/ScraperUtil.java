package com.example.saudeapiback.utils;
import com.example.saudeapiback.models.estabelecimento.Estabelecimento;
import com.example.saudeapiback.repositories.EstabelecimentoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ScraperUtil {

    // Checar isoladoa em codigo_tipo_unidade

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    private final String apiUrl = "https://apidadosabertos.saude.gov.br/cnes/estabelecimentos";
    private RestTemplate restTemplate = new RestTemplate();

    private final int thread_number = 40;

    private final ExecutorService executor = Executors.newFixedThreadPool(thread_number);

    private final AtomicInteger totalEstablishmentsReceived = new AtomicInteger(0);

    public void getAndPopulateEstablishments() {
        Map<String, String> stateCodes = loadCodes("codigos_estados.txt");
        Map<String, String> unitTypes = loadCodes("TiposUnidades.txt");
        System.out.println(thread_number);

        CountDownLatch latch = new CountDownLatch(stateCodes.size() * unitTypes.size());

        for (Map.Entry<String, String> stateEntry : stateCodes.entrySet()) {
            String state = stateEntry.getKey();
            String stateCode = stateEntry.getValue();

            for (Map.Entry<String, String> unitEntry : unitTypes.entrySet()) {
                String unit = unitEntry.getKey();
                String unitCode = unitEntry.getValue();

                executor.submit(() -> {
                    try {
                        Map<String, String> params = new HashMap<>();
                        params.put("codigo_tipo_unidade", unitCode);
                        params.put("codigo_uf", stateCode);
                        params.put("status", "1");
                        params.put("limit", "20");
                        params.put("offset", "50");

                        Map<String, Object> response = fetchEstablishments(params);
                        if (response.containsKey("estabelecimentos")) {
                            List<Map<String, Object>> estabelecimentos = (List<Map<String, Object>>) response.get("estabelecimentos");

                            totalEstablishmentsReceived.addAndGet(estabelecimentos.size());
                            System.out.println("Estabelecimentos recebidos até agora: " + totalEstablishmentsReceived.get());

                            for (Map<String, Object> estabelecimentoData : estabelecimentos) {
                                Estabelecimento estabelecimento = convertToEstabelecimento(estabelecimentoData);
                                upsertEstabelecimento(estabelecimento);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        latch.countDown();
                    }
                });
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Atualização interrompida: " + e.getMessage());
        }

        executor.shutdown();
        System.out.println("Processo de recebimento de estabelecimentos finalizado.");
    }

    public Map<String, String> loadCodes(String filePath) {
        Map<String, String> codes = new HashMap<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(" - ")) {
                    String[] parts = line.split(" - ");
                    codes.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codes;
    }

    public Map<String, Object> fetchEstablishments(Map<String, String> params) throws RestClientException {
        return restTemplate.getForObject(apiUrl + buildUrlParams(params), Map.class);
    }

    private String buildUrlParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder("?");
        params.forEach((key, value) -> sb.append(key).append("=").append(value).append("&"));
        return sb.toString();
    }

    public void upsertEstabelecimento(Estabelecimento estabelecimento) {
        Estabelecimento existingEstabelecimento = estabelecimentoRepository.findByCodigoCnes(estabelecimento.getCodigoCnes());

        if (existingEstabelecimento != null) {
            boolean updated = false;

            Field[] fields = Estabelecimento.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                try {
                    Object currentValue = field.get(existingEstabelecimento);
                    Object newValue = field.get(estabelecimento);

                    if (field.getName().equals("latitudeEstabelecimentoDecimoGrau") || field.getName().equals("longitudeEstabelecimentoDecimoGrau")) {
                        BigDecimal roundedCurrentValue = currentValue != null ? new BigDecimal(currentValue.toString()).setScale(4, BigDecimal.ROUND_HALF_UP) : null;
                        BigDecimal roundedNewValue = newValue != null ? new BigDecimal(newValue.toString()).setScale(4, BigDecimal.ROUND_HALF_UP) : null;

                        if (roundedNewValue != null && !roundedNewValue.equals(roundedCurrentValue)) {
                            System.out.println("Campo: " + field.getName());
                            System.out.println("Valor no banco de dados (arredondado): " + roundedCurrentValue);
                            System.out.println("Valor recebido da API (arredondado): " + roundedNewValue);

                            field.set(existingEstabelecimento, newValue);
                            updated = true;
                        }
                    } else {

                        if (newValue != null && (currentValue == null || !currentValue.equals(newValue))) {
                            System.out.println("Campo: " + field.getName());
                            System.out.println("Valor no banco de dados: " + currentValue);
                            System.out.println("Valor recebido da API: " + newValue);

                            field.set(existingEstabelecimento, newValue);
                            updated = true;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (updated) {
                estabelecimentoRepository.save(existingEstabelecimento);
                System.out.println("Estabelecimento com código CNES " + estabelecimento.getCodigoCnes() + " atualizado.");
            }
        } else {
            estabelecimentoRepository.save(estabelecimento);
            System.out.println("Novo estabelecimento inserido com código CNES " + estabelecimento.getCodigoCnes());
        }
    }

    private Estabelecimento convertToEstabelecimento(Map<String, Object> data) {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setCodigoCnes((Integer) data.get("codigo_cnes"));
        estabelecimento.setNumeroCnpjEntidade((String) data.get("numero_cnpj_entidade"));
        estabelecimento.setNomeRazaoSocial((String) data.get("nome_razao_social"));
        estabelecimento.setNomeFantasia((String) data.get("nome_fantasia"));
        estabelecimento.setNaturezaOrganizacaoEntidade((String) data.get("natureza_organizacao_entidade"));
        estabelecimento.setTipoGestao((String) data.get("tipo_gestao"));
        estabelecimento.setDescricaoNivelHierarquia((String) data.get("descricao_nivel_hierarquia"));
        estabelecimento.setDescricaoEsferaAdministrativa((String) data.get("descricao_esfera_administrativa"));
        estabelecimento.setCodigoTipoUnidade((Integer) data.get("codigo_tipo_unidade"));
        estabelecimento.setCodigoCepEstabelecimento((String) data.get("codigo_cep_estabelecimento"));
        estabelecimento.setEnderecoEstabelecimento((String) data.get("endereco_estabelecimento"));
        estabelecimento.setNumeroEstabelecimento((String) data.get("numero_estabelecimento"));
        estabelecimento.setBairroEstabelecimento((String) data.get("bairro_estabelecimento"));
        estabelecimento.setNumeroTelefoneEstabelecimento((String) data.get("numero_telefone_estabelecimento"));
        estabelecimento.setLatitudeEstabelecimentoDecimoGrau((Double) data.get("latitude_estabelecimento_decimo_grau"));
        estabelecimento.setLongitudeEstabelecimentoDecimoGrau((Double) data.get("longitude_estabelecimento_decimo_grau"));
        estabelecimento.setEnderecoEmailEstabelecimento((String) data.get("endereco_email_estabelecimento"));
        estabelecimento.setNumeroCnpj((String) data.get("numero_cnpj"));
        estabelecimento.setCodigoIdentificadorTurnoAtendimento((String) data.get("codigo_identificador_turno_atendimento"));
        estabelecimento.setDescricaoTurnoAtendimento((String) data.get("descricao_turno_atendimento"));
        estabelecimento.setEstabelecimentoFazAtendimentoAmbulatorialSus((String) data.get("estabelecimento_faz_atendimento_ambulatorial_sus"));
        estabelecimento.setCodigoEstabelecimentoSaude((String) data.get("codigo_estabelecimento_saude"));
        estabelecimento.setCodigoUf((Integer) data.get("codigo_uf"));
        estabelecimento.setCodigoMunicipio((Integer) data.get("codigo_municipio"));
        estabelecimento.setDescricaoNaturezaJuridicaEstabelecimento((String) data.get("descricao_natureza_juridica_estabelecimento"));
        estabelecimento.setCodigoMotivoDesabilitacaoEstabelecimento((String) data.get("codigo_motivo_desabilitacao_estabelecimento"));
        estabelecimento.setEstabelecimentoPossuiCentroCirurgico((Integer) data.get("estabelecimento_possui_centro_cirurgico"));
        estabelecimento.setEstabelecimentoPossuiCentroObstetrico((Integer) data.get("estabelecimento_possui_centro_obstetrico"));
        estabelecimento.setEstabelecimentoPossuiCentroNeonatal((Integer) data.get("estabelecimento_possui_centro_neonatal"));
        estabelecimento.setEstabelecimentoPossuiAtendimentoHospitalar((Integer) data.get("estabelecimento_possui_atendimento_hospitalar"));
        estabelecimento.setEstabelecimentoPossuiServicoApoio((Integer) data.get("estabelecimento_possui_servico_apoio"));
        estabelecimento.setEstabelecimentoPossuiAtendimentoAmbulatorial((Integer) data.get("estabelecimento_possui_atendimento_ambulatorial"));
        estabelecimento.setCodigoAtividadeEnsinoUnidade((String) data.get("codigo_atividade_ensino_unidade"));
        estabelecimento.setCodigoNaturezaOrganizacaoUnidade((String) data.get("codigo_natureza_organizacao_unidade"));
        estabelecimento.setCodigoNivelHierarquiaUnidade((String) data.get("codigo_nivel_hierarquia_unidade"));
        estabelecimento.setCodigoEsferaAdministrativaUnidade((String) data.get("codigo_esfera_administrativa_unidade"));

        return estabelecimento;
    }
}
