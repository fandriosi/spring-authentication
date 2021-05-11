package com.andriosi.fabio.authentication.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TrocaHidrometro {
    private String dataITrocaHidrometro;
    private String DataFTrocaHidrometro;

    public String getDataITrocaHidrometro() {
        return dataITrocaHidrometro;
    }

    public void setDataITrocaHidrometro(Date data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // TODO: handle exception
        if(data != null){
            this.dataITrocaHidrometro = format.format(data);
        }

    }

    public String getDataFTrocaHidrometro() {
        return DataFTrocaHidrometro;
    }

    public void setDataFTrocaHidrometro(Date data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // TODO: handle exception
        if(data != null){
            this.dataITrocaHidrometro = format.format(data);
        }

    }
}
