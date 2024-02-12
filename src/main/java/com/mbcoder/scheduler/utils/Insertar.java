package com.mbcoder.scheduler.utils;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Insertar {

    public static void main(String[] args) {


        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=dbIne;TrustServerCertificate=True;";
        String user = "sa";
        String pass = "Mipasword2";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pass);
             Statement stmt = conn.createStatement();
        ) {


            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdh1 = new SimpleDateFormat("HH:mm:ss");

            String strDate3 = null;
            String strhour1 = null;

            int contador = 0;
            int registros_bacht = 100;//cambiar el tamaño de lote
            int hora = 0;
            int min = 0;
            calendar.add(Calendar.MINUTE, min + 5);//aumenta 5 min
            for (int i = 0; i <= 100; i++) {//se insertan 600 registros

                if (i == 200) {
                    hora += 1;
                    //Agregamos las horas deseadas
                    calendar.add(Calendar.HOUR, 1);//aumentar 1hora a partir del registro 200
                    if (hora == 23) {
                        hora = 0;
                    }
                }

                if (i == 50) {
                    min += 1;
                    //Agregamos los minutos deseados
                    calendar.add(Calendar.MINUTE, 5);//aumentar 5 min a partir del registro 200
                    if (min == 59) {
                        min = 0;
                    }
                }

                strDate3 = sdf1.format(calendar.getTime());
                strhour1 = sdh1.format(calendar.getTime());


                String insertSql = "INSERT INTO REGISTROS (Dia, Hora, Status) VALUES "
                        + "('" + strDate3 + "','" + strhour1 + "','En Proceso');";

                stmt.addBatch(insertSql);
                contador += 1;
                if (contador == registros_bacht) {
                    stmt.executeBatch();
                    contador = 0;
                }
            }
            System.out.println("SUCCES");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }



}
