package com.andriosi.fabio.authentication.session;

import com.andriosi.fabio.authentication.entity.*;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SseService extends DataBaseManager {
    private Hidrometro h;
    public List<Sse> findAll(){
        List<Sse> list = new ArrayList<>();
        Sse s;
        String sql = "SELECT GA40NREC, GA40DREC, GA40HREC, GA40TATE, GA19DESD, GA40CLOG, GA40ORIG, GA40TRNS,";
        sql += " GA40NUM1, GA15CONS, GA15OBSR, GA15OBS2 FROM "+getTable("GA40")+","+getTable("GA15") ;
        sql += ","+getTable("GA19")+" WHERE GA40DREC = '2019-02-13' AND GA40NREC=GA15NREC AND GA40CODD=GA19CODD ";
        sql += "AND GA40TATE='RE01' WITH UR";

        try(PreparedStatement pst = getConnection().prepareStatement(sql)) {
            try(ResultSet rs = pst.executeQuery()){
                while (rs.next()){
                    s=new Sse();
                    s.setNumeroSse(rs.getInt(1));
                    s.setDataGeracao(rs.getDate(2));
                    s.setHoraGeracao(rs.getTime(3));
                    s.setCodigoServico(rs.getString(4).trim());
                    s.setSetorExecutante(rs.getString(5));
                    s.setCodigoLogradouro(rs.getInt(6));
                    s.setSseOrigem(rs.getInt(7));
                    s.setSetorSolicitante(String.valueOf(rs.getInt(8)));
                    s.setNumeroEndereco(rs.getInt(9));
                    s.setConsumidor(rs.getInt(10));
                    if(rs.getString(12) != null)
                        s.setObservacao(rs.getString(11).trim()+" "+rs.getString(12).trim());
                    else
                        s.setObservacao(rs.getString(11).trim());

                    list.add(s);
                }
            }
        } catch (SQLException | ClassNotFoundException ex){
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach((i) ->{
            i.setUsuario(this.getUsuario(i.getNumeroSse()));
            i.setFlagCongas(this.getFlagCongas(i.getCodigoLogradouro()));
            i.setFlagTrocaDeRede(this.getTrocaRede(i.getConsumidor()));
            i.setHidrometro(this.getHidrometro(i.getConsumidor()));
            i.setEconomia(this.getEconomia(i.getConsumidor()));
            i.setEndereco(this.getEndereco(i.getNumeroSse()));
            i.setPrioridade(this.getPrioridade(i.getCodigoServico()));
            i.setQuadra(this.getQuadra(i.getConsumidor()));
            i.setLacre(this.getLacre(i.getConsumidor()));
            i.setCorte(this.getCorte(i.getConsumidor()));
            if(i.getQuadra()!= null)
                i.setTrocaHidrometro(this.getTrocaHidrometro(i.getQuadra().getRegistro()));
            if(i.getEconomia() != null)
                i.setFlagTipoLigacao(this.getTipoLigacao(i.getEconomia().getCodigoCatergoria()));
            i.setCodigoOcorrenciaDenuncia(this.getDenuncia(i.getNumeroSse()));
        });
        return list;
    }
    private Hidrometro getHidrometro(int codigoConsumidor){
        Hidrometro h = null;
        try{
            String sql = "SELECT HD01VAZA, HD01ANOF, HD01MARC, HD01HIDR, HD01SERI, HD01DIGI, HD01TIPO, HD01DTUM FROM " ;
            sql += getTable("HD01")+" WHERE HD01CONS = ? WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setInt(1, codigoConsumidor);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        h = new Hidrometro();
                        h.setVazao(rs.getString(1).trim());
                        h.setAno(rs.getInt(2));
                        h.setMarca(rs.getString(3).trim());
                        h.setNumero(rs.getInt(4));
                        h.setSerie(rs.getString(5).trim());
                        h.setDigito(rs.getInt(6));
                        h.setTipo(rs.getString(7));
                        h.setUltimaInstalacao(rs.getDate(8));
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }
    private String getUsuario(long numeroSse){
        String s = "";
        try{
            String sql = "SELECT GA49IDEN FROM "+getTable("GA49") ;
            sql +=" WHERE GA49NREC = ? AND GA49STAT='I' WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setLong(1, numeroSse);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        s = rs.getString(1).trim();
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    private Prioridade getPrioridade(String tipoServico){
        Prioridade p = null;
        try{
            String sql = "SELECT GA43HORA, GA16TIPH FROM "+getTable("GA43")+","+getTable("GA16") ;
            sql +=" WHERE GA16TATE = ? AND  GA16TIPR=GA43TIPR AND GA16PRIO=GA43PRIO  WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setString(1, tipoServico);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        p = new Prioridade();
                        p.setTempoPrioridade(rs.getFloat(1));
                        p.setTipoPrioridade(rs.getString(2).trim());
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    private Economia getEconomia(int codigoConsumidor){
        Economia e = null;
        try{
            String sql = "SELECT AJ11NOME, AJ11ECON, AJ11MEDI, AJ11MARC, AJ11CATE, AJ11ROTE, AJ11PADR FROM ";
            sql += getTable("AJ11")+" WHERE AJ11CONS=?  WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setInt(1, codigoConsumidor);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        e = new Economia();
                        e.setNomeConsumidor(rs.getString(1).trim());
                        e.setQuantidadeEconomias(rs.getInt(2));
                        e.setConsumoMedio(rs.getInt(3));
                        e.setConsumoAnterior(rs.getInt(4));
                        e.setCodigoCatergoria(rs.getInt(5));
                        e.setRoteiro(rs.getString(6));
                        e.setPadraoLigacao(rs.getString(7));
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    private Endereco getEndereco(long numeroSse){
        Endereco e = null;
        try{
            String sql = "SELECT AEAJTLOG, AEAJNLOG, GA40NUM1, AJ11COMP, AEAJCBAI, AEAJBAIR, AJ11REFE FROM ";
            sql += getTable("GA40")+","+getTable("AJ11")+","+getTable("GA15")+","+getTable("AEAJ")+ " WHERE GA40NREC=?  ";
            sql += "AND GA40NREC=GA15NREC AND GA15CONS=AJ11CONS AND GA40CLOG=AEAJLOGR WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setLong(1, numeroSse);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        e = new Endereco();
                        e.setTipoLogradouro(rs.getString(1).trim());
                        e.setEndereco(rs.getString(2).trim());
                        e.setNumero(rs.getInt(3));
                        e.setComplemento(rs.getString(4).trim());
                        e.setCodigoBairro(rs.getInt(5));
                        e.setNomeBairro(rs.getString(6).trim());
                        e.setReferenciaEndereco(rs.getString(7).trim());
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    private Quadra getQuadra(int codigoConsumidor){
        Quadra q = null;
        try{
            String sql = "SELECT AJ85REGI, AJ85CFOL, AJ85CPAR, AJ85CQUA, AJ85CLOT, AJ85CLIG, AJ85QUAR, AJ85QUAD, AJ85LOTE, ";
            sql += "AJ85CLAS, AJ85FONT FROM "+getTable("AJ85")+" WHERE AJ85CONS=? WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setInt(1, codigoConsumidor);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        q = new Quadra();
                        q.setRegistro(rs.getInt(1));
                        q.setCodigoCartografico(rs.getString(2)+""+rs.getString(3)+""+rs.getString(4)+
                                ""+rs.getString(5)+""+rs.getString(6));
                        q.setQuarteirao(rs.getInt(7));
                        q.setQuadra(rs.getString(8).trim());
                        q.setQuadraLote(rs.getString(9).trim());
                        q.setCodigoClassificao(rs.getString(10));
                        q.setFonteAlternativa(rs.getString(11));
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
    private Lacre getLacre(int codigoConsumidor){
        Lacre l = null;
        if(codigoConsumidor > 0){
            try{
                String sql = "SELECT HD07ANOF, HD07TPLC, HD07LACR FROM "+getTable("HD07");
                sql += " WHERE HD07CONS=? AND HD07ATIV=' '  WITH UR";
                try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                    pst.setInt(1, codigoConsumidor);
                    try(ResultSet rs = pst.executeQuery()){
                        while(rs.next()){
                            l = new Lacre();
                            l.setAnolacre(rs.getInt(1));
                            l.setTipoLacre(rs.getString(2));
                            l.setNumeroLacre(rs.getInt(3));
                        }
                    }
                }
            }catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l;
    }

    private String getFlagCongas(int codigoLogradouro){
        int f = 0;
        String c = "";
        try{
            String sql = "SELECT  GA93CLOG FROM "+getTable("GA93");
            sql += " WHERE GA93CLOG=?  WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setInt(1, codigoLogradouro);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        f = rs.getInt(1);
                    }
                }
            }

        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(f > 0)
            return c ="1";
        else
            return c ="0";
    }

    private String getDenuncia(long codigoSse){
        String d = "";
        try{
            String sql = "SELECT  GA53OCO2 FROM "+getTable("GA53");
            sql += " WHERE GA53NREC=?  WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setLong(1, codigoSse);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        d = rs.getString(1);
                    }
                }
            }

        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    private String getTrocaRede(int codigoConsumidor){
        String r = "";
        int c = 0;
        if(codigoConsumidor > 0){
            try{
                String sql = "SELECT  GA88CONS FROM "+getTable("GA88");
                sql += " WHERE GA88CONS=?  WITH UR";
                try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                    pst.setInt(1, codigoConsumidor);
                    try(ResultSet rs = pst.executeQuery()){
                        while(rs.next()){
                            c= rs.getInt(1);
                        }
                    }
                }

            }catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(c > 0)
            return r ="1";
        else
            return r;
    }
    private String getTipoLigacao(int codigoCategoria){
        String l = "";
        if(codigoCategoria > 0){
            try{
                String sql = "SELECT  AJ08SERV FROM "+getTable("AJ08");
                sql += " WHERE AJ08CATE=?  WITH UR";
                try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                    pst.setInt(1, codigoCategoria);
                    try(ResultSet rs = pst.executeQuery()){
                        while(rs.next()){
                            l= rs.getString(1);
                        }
                    }
                }

            }catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return l ;
    }
    private Corte getCorte(int codigoConsumidor){
        Corte c = null;
        if(codigoConsumidor > 0){
            try{
                String sql = "SELECT  GJ04DEVE, GJ04FASE, GJ04OCOR, GJ04MODA FROM "+getTable("GJ04");
                sql += " WHERE GJ04CONS=?  WITH UR";
                try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                    pst.setInt(1, codigoConsumidor);
                    try(ResultSet rs = pst.executeQuery()){
                        while(rs.next()){
                            c = new Corte();
                            c.setData(rs.getDate(1));
                            c.setFase(rs.getString(2));
                            c.setOcorrencia(rs.getString(3));
                            c.setModalidade(rs.getString(4));
                        }
                    }
                }

            }catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }

    private TrocaHidrometro getTrocaHidrometro(int registro){
        TrocaHidrometro th = new TrocaHidrometro();
        try{
            String sql = "SELECT  FR01DTF2 + 1 DAYS, FR01DTFT FROM "+getTable("FR01");
            sql += " WHERE FR01REGI=? ORDER BY FR01DTFT WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setInt(1, registro);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next() && th.getDataITrocaHidrometro() == null){
                        th.setDataITrocaHidrometro(rs.getDate(1));
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            String sql = "SELECT  FR01DTLE - 3 DAYS FROM "+getTable("FR01");
            sql += " WHERE FR01REGI=? AND ((FR01DTLE - 3 DAYS ) > CURRENT DATE ";
            sql += " AND FR01DTF2 > CURRENT DATE) WITH UR";
            try(PreparedStatement pst = getConnection().prepareStatement(sql)){
                pst.setInt(1, registro);
                try(ResultSet rs = pst.executeQuery()){
                    while(rs.next()){
                        th.setDataFTrocaHidrometro(rs.getDate(1));
                    }
                }
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return th;
    }

}
