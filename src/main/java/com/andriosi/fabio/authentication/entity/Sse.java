package com.andriosi.fabio.authentication.entity;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class Sse {
    private long numeroSse;
    private int sseOrigem;
    private String dataGeracao;
    private String horaGeracao;
    private String CodigoServico;
    private String setorSolicitante;
    private String setorExecutante;
    private String usuario;
    private int codigoLogradouro;
    private String observacao;
    private int numeroEndereco;
    private int consumidor;
    private String flagCongas;
    private String flagTrocaDeRede;
    private String flagTipoLigacao;
    private String codigoOcorrenciaDenuncia;
    private Hidrometro hidrometro;
    private Economia economia;
    private Endereco endereco;
    private Prioridade prioridade;
    private Quadra quadra;
    private Lacre lacre;
    private Corte corte;
    private TrocaHidrometro trocaHidrometro;
    
    public long getNumeroSse() {
        return numeroSse;
    }

    public void setNumeroSse(long numeroSse) {
        this.numeroSse = numeroSse;
    }

    public int getSseOrigem() {
        return sseOrigem;
    }

    public void setSseOrigem(int sseOrigem) {
        this.sseOrigem = sseOrigem;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date data){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // TODO: handle exception
        if(data != null){
            this.dataGeracao = format.format(data);
        }
    }

    public String getHoraGeracao() {
        return horaGeracao;
    }

    public void setHoraGeracao(Time time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // TODO: handle exception
        if(time != null){
            this.horaGeracao = format.format(time);
        }

    }

    public String getCodigoServico() {
        return CodigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        CodigoServico = codigoServico;
    }

    public String getSetorSolicitante() {
        return setorSolicitante;
    }

    public void setSetorSolicitante(String setorSolicitante) {
        this.setorSolicitante = setorSolicitante;
    }

    public String getSetorExecutante() {
        return setorExecutante;
    }

    public void setSetorExecutante(String setorExecutante) {
        this.setorExecutante = setorExecutante;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCodigoLogradouro() {
        return codigoLogradouro;
    }

    public void setCodigoLogradouro(int codigoLogradouro) {
        this.codigoLogradouro = codigoLogradouro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public int getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(int consumidor) {
        this.consumidor = consumidor;
    }

    public String getFlagCongas() {
        return flagCongas;
    }

    public void setFlagCongas(String flagCongas) {
        this.flagCongas = flagCongas;
    }

    public String getFlagTrocaDeRede() {
        return flagTrocaDeRede;
    }

    public void setFlagTrocaDeRede(String flagTrocaDeRede) {
        this.flagTrocaDeRede = flagTrocaDeRede;
    }

    public String getFlagTipoLigacao() {
        return flagTipoLigacao;
    }

    public void setFlagTipoLigacao(String flagTipoLigacao) {
        this.flagTipoLigacao = flagTipoLigacao;
    }

    public String getCodigoOcorrenciaDenuncia() {
        return codigoOcorrenciaDenuncia;
    }

    public void setCodigoOcorrenciaDenuncia(String codigoOcorrenciaDenuncia) {
        this.codigoOcorrenciaDenuncia = codigoOcorrenciaDenuncia;
    }

    public Hidrometro getHidrometro() {
        return hidrometro;
    }

    public void setHidrometro(Hidrometro hidrometro) {
        this.hidrometro = hidrometro;
    }

    public Economia getEconomia() {
        return economia;
    }

    public void setEconomia(Economia economia) {
        this.economia = economia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Lacre getLacre() {
        return lacre;
    }

    public void setLacre(Lacre lacre) {
        this.lacre = lacre;
    }

    public Corte getCorte() {
        return corte;
    }

    public void setCorte(Corte corte) {
        this.corte = corte;
    }

    public TrocaHidrometro getTrocaHidrometro() {
        return trocaHidrometro;
    }

    public void setTrocaHidrometro(TrocaHidrometro trocaHidrometro) {
        this.trocaHidrometro = trocaHidrometro;
    }
}
