package com.andriosi.fabio.authentication.entity;

public class Economia {
    private int codigoCatergoria;
    private int quantidadeEconomias;
    private String nomeConsumidor;
    private int consumoMedio;
    private int consumoAnterior;
    private String roteiro;
    private String padraoLigacao;

    public int getCodigoCatergoria() {
        return codigoCatergoria;
    }

    public void setCodigoCatergoria(int codigoCatergoria) {
        this.codigoCatergoria = codigoCatergoria;
    }

    public int getQuantidadeEconomias() {
        return quantidadeEconomias;
    }

    public void setQuantidadeEconomias(int quantidadeEconomias) {
        this.quantidadeEconomias = quantidadeEconomias;
    }

    public String getNomeConsumidor() {
        return nomeConsumidor;
    }

    public void setNomeConsumidor(String nomeConsumidor) {
        this.nomeConsumidor = nomeConsumidor;
    }

    public int getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(int consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public int getConsumoAnterior() {
        return consumoAnterior;
    }

    public void setConsumoAnterior(int consumoAnterior) {
        this.consumoAnterior = consumoAnterior;
    }

    public String getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(String roteiro) {
        this.roteiro = roteiro;
    }

    public String getPadraoLigacao() {
        return padraoLigacao;
    }

    public void setPadraoLigacao(String padraoLigacao) {
        this.padraoLigacao = padraoLigacao;
    }
}
