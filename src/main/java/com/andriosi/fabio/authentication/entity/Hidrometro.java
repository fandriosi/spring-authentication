package com.andriosi.fabio.authentication.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Hidrometro {
    private String vazao;
    private int ano;
    private String marca;
    private int numero;
    private String serie;
    private int digito;
    private String tipo;
    private String ultimaInstalacao;

    public String getVazao() {
        return vazao;
    }

    public void setVazao(String vazao) {
        this.vazao = vazao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getDigito() {
        return digito;
    }

    public void setDigito(int digito) {
        this.digito = digito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUltimaInstalacao() {
        return ultimaInstalacao;
    }

    public void setUltimaInstalacao(Date data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // TODO: handle exception
        if(data != null){
            this.ultimaInstalacao= format.format(data);
        }
    }
}
