package com.andriosi.fabio.authentication.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Corte {
    private String data;
    private String fase;
    private String ocorrencia;
    private String modalidade;

    public String getData() {
        return data;
    }

    public void setData(Date data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // TODO: handle exception
        if(data != null){
            this.data= format.format(data);
        }

    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
