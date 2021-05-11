package com.andriosi.fabio.authentication.session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {
    private static final String JDBC_DRIVER ="com.ibm.db2.jcc.DB2Driver";//
    private static final String JDBC_DB_URL="jdbc:db2://10.1.2.131:50000/TSTMDB";
    //produçao   private static final String JDBC_DB_URL="jdbc:db2://10.1.2.131:50000/PRDMDB";
    //private static final String JDBC_USER ="SELLECTA";//PJAVSGEO
    private static final String JDBC_USER ="SELLECTA";//PJAVSGEO
    // private static final String JDBC_USER ="PJAVSGEO";
    private static final String JDBC_PASSWORD ="S1L3C@0W";
    //private static final String JDBC_PASSWORD ="O$1U3_A@";
    private static final String DB2_OWNER ="TEST";
    //private static final String DB2_OWNER ="PJAVSGEO";
    private static final String DB2_OWNERGA ="T02GA";
    //private static final String DB2_OWNERAJ ="D02AJ";
    private static final String DB2_OWNERAJ ="D02AJTST";
    //private static final String DB2_OWNERFR ="PRODFR";
    private static final String DB2_OWNERFR ="TESTFR";

    private final Map<String, String> tabels = new HashMap<>();

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(JDBC_DRIVER);
        return  DriverManager.getConnection(JDBC_DB_URL,JDBC_USER,JDBC_PASSWORD);
    }

    protected String getTable(String key){
        tabels.put("GA15", DB2_OWNERGA+".GA15");
        tabels.put("GA40", DB2_OWNERGA+".GA40");
        tabels.put("GA19", DB2_OWNERGA+".GA19");
        tabels.put("GA49", DB2_OWNERGA+".GA49");
        tabels.put("GA43", DB2_OWNERGA+".GA43");
        tabels.put("GA16", DB2_OWNERGA+".GA16");
        tabels.put("GA93", DB2_OWNERGA+".GA93");
        tabels.put("GA53", DB2_OWNERGA+".GA53");
        tabels.put("GA88", DB2_OWNERGA+".GA88");
        tabels.put("HD01", DB2_OWNER+".HD01");
        tabels.put("HD07", DB2_OWNER+".HD07");
        tabels.put("GJ04", DB2_OWNER+".GJ04");
        tabels.put("AJ11", DB2_OWNERAJ+".AJ11");
        tabels.put("AJ85", DB2_OWNERAJ+".AJ85");
        tabels.put("AJ08", DB2_OWNERAJ+".AJ08");
        tabels.put("AEAJ", DB2_OWNER+".AEAJ");
        tabels.put("FR01", DB2_OWNERFR+".FR01");
        return tabels.get(key);
    }
}
