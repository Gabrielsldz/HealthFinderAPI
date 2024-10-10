package com.example.saudeapiback.domain.estabelecimento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estabelecimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigoCnes;
    private String numeroCnpjEntidade;
    private String nomeRazaoSocial;
    private String nomeFantasia;
    private String naturezaOrganizacaoEntidade;
    private String tipoGestao;
    private String descricaoNivelHierarquia;
    private String descricaoEsferaAdministrativa;
    private int codigoTipoUnidade;
    private String codigoCepEstabelecimento;
    private String enderecoEstabelecimento;
    private String numeroEstabelecimento;
    private String bairroEstabelecimento;
    private String numeroTelefoneEstabelecimento;
    private Float latitudeEstabelecimentoDecimoGrau;
    private Float longitudeEstabelecimentoDecimoGrau;
    private String enderecoEmailEstabelecimento;
    private String numeroCnpj;
    private String codigoIdentificadorTurnoAtendimento;
    private String descricaoTurnoAtendimento;
    private String estabelecimentoFazAtendimentoAmbulatorialSus;
    private String codigoEstabelecimentoSaude;
    private int codigoUf;
    private int codigoMunicipio;
    private String descricaoNaturezaJuridicaEstabelecimento;
    private String codigoMotivoDesabilitacaoEstabelecimento;
    private Integer estabelecimentoPossuiCentroCirurgico;
    private Integer estabelecimentoPossuiCentroObstetrico;
    private Integer estabelecimentoPossuiCentroNeonatal;
    private Integer estabelecimentoPossuiAtendimentoHospitalar;
    private Integer estabelecimentoPossuiServicoApoio;
    private Integer estabelecimentoPossuiAtendimentoAmbulatorial;
    private String codigoAtividadeEnsinoUnidade;
    private String codigoNaturezaOrganizacaoUnidade;
    private String codigoNivelHierarquiaUnidade;
    private String codigoEsferaAdministrativaUnidade;

}