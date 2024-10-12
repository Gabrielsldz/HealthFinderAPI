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
    private Double latitudeEstabelecimentoDecimoGrau;
    private Double longitudeEstabelecimentoDecimoGrau;
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

    // Método para atualizar os campos de um registro existente com novos dados
    public void updateFrom(Estabelecimento newData) {
        if (newData.getNomeFantasia() != null) this.nomeFantasia = newData.getNomeFantasia();
        if (newData.getNumeroCnpjEntidade() != null) this.numeroCnpjEntidade = newData.getNumeroCnpjEntidade();
        if (newData.getNomeRazaoSocial() != null) this.nomeRazaoSocial = newData.getNomeRazaoSocial();
        if (newData.getNaturezaOrganizacaoEntidade() != null) this.naturezaOrganizacaoEntidade = newData.getNaturezaOrganizacaoEntidade();
        if (newData.getTipoGestao() != null) this.tipoGestao = newData.getTipoGestao();
        if (newData.getDescricaoNivelHierarquia() != null) this.descricaoNivelHierarquia = newData.getDescricaoNivelHierarquia();
        if (newData.getDescricaoEsferaAdministrativa() != null) this.descricaoEsferaAdministrativa = newData.getDescricaoEsferaAdministrativa();
        if (newData.getCodigoTipoUnidade() != 0) this.codigoTipoUnidade = newData.getCodigoTipoUnidade();
        if (newData.getCodigoCepEstabelecimento() != null) this.codigoCepEstabelecimento = newData.getCodigoCepEstabelecimento();
        if (newData.getEnderecoEstabelecimento() != null) this.enderecoEstabelecimento = newData.getEnderecoEstabelecimento();
        if (newData.getNumeroEstabelecimento() != null) this.numeroEstabelecimento = newData.getNumeroEstabelecimento();
        if (newData.getBairroEstabelecimento() != null) this.bairroEstabelecimento = newData.getBairroEstabelecimento();
        if (newData.getNumeroTelefoneEstabelecimento() != null) this.numeroTelefoneEstabelecimento = newData.getNumeroTelefoneEstabelecimento();
        if (newData.getLatitudeEstabelecimentoDecimoGrau() != null) this.latitudeEstabelecimentoDecimoGrau = newData.getLatitudeEstabelecimentoDecimoGrau();
        if (newData.getLongitudeEstabelecimentoDecimoGrau() != null) this.longitudeEstabelecimentoDecimoGrau = newData.getLongitudeEstabelecimentoDecimoGrau();
        if (newData.getEnderecoEmailEstabelecimento() != null) this.enderecoEmailEstabelecimento = newData.getEnderecoEmailEstabelecimento();
        if (newData.getNumeroCnpj() != null) this.numeroCnpj = newData.getNumeroCnpj();
        if (newData.getCodigoIdentificadorTurnoAtendimento() != null) this.codigoIdentificadorTurnoAtendimento = newData.getCodigoIdentificadorTurnoAtendimento();
        if (newData.getDescricaoTurnoAtendimento() != null) this.descricaoTurnoAtendimento = newData.getDescricaoTurnoAtendimento();
        if (newData.getEstabelecimentoFazAtendimentoAmbulatorialSus() != null) this.estabelecimentoFazAtendimentoAmbulatorialSus = newData.getEstabelecimentoFazAtendimentoAmbulatorialSus();
        if (newData.getCodigoEstabelecimentoSaude() != null) this.codigoEstabelecimentoSaude = newData.getCodigoEstabelecimentoSaude();
        if (newData.getCodigoUf() != 0) this.codigoUf = newData.getCodigoUf();
        if (newData.getCodigoMunicipio() != 0) this.codigoMunicipio = newData.getCodigoMunicipio();
        if (newData.getDescricaoNaturezaJuridicaEstabelecimento() != null) this.descricaoNaturezaJuridicaEstabelecimento = newData.getDescricaoNaturezaJuridicaEstabelecimento();
        if (newData.getCodigoMotivoDesabilitacaoEstabelecimento() != null) this.codigoMotivoDesabilitacaoEstabelecimento = newData.getCodigoMotivoDesabilitacaoEstabelecimento();
        if (newData.getEstabelecimentoPossuiCentroCirurgico() != null) this.estabelecimentoPossuiCentroCirurgico = newData.getEstabelecimentoPossuiCentroCirurgico();
        if (newData.getEstabelecimentoPossuiCentroObstetrico() != null) this.estabelecimentoPossuiCentroObstetrico = newData.getEstabelecimentoPossuiCentroObstetrico();
        if (newData.getEstabelecimentoPossuiCentroNeonatal() != null) this.estabelecimentoPossuiCentroNeonatal = newData.getEstabelecimentoPossuiCentroNeonatal();
        if (newData.getEstabelecimentoPossuiAtendimentoHospitalar() != null) this.estabelecimentoPossuiAtendimentoHospitalar = newData.getEstabelecimentoPossuiAtendimentoHospitalar();
        if (newData.getEstabelecimentoPossuiServicoApoio() != null) this.estabelecimentoPossuiServicoApoio = newData.getEstabelecimentoPossuiServicoApoio();
        if (newData.getEstabelecimentoPossuiAtendimentoAmbulatorial() != null) this.estabelecimentoPossuiAtendimentoAmbulatorial = newData.getEstabelecimentoPossuiAtendimentoAmbulatorial();
        if (newData.getCodigoAtividadeEnsinoUnidade() != null) this.codigoAtividadeEnsinoUnidade = newData.getCodigoAtividadeEnsinoUnidade();
        if (newData.getCodigoNaturezaOrganizacaoUnidade() != null) this.codigoNaturezaOrganizacaoUnidade = newData.getCodigoNaturezaOrganizacaoUnidade();
        if (newData.getCodigoNivelHierarquiaUnidade() != null) this.codigoNivelHierarquiaUnidade = newData.getCodigoNivelHierarquiaUnidade();
        if (newData.getCodigoEsferaAdministrativaUnidade() != null) this.codigoEsferaAdministrativaUnidade = newData.getCodigoEsferaAdministrativaUnidade();
    }
}