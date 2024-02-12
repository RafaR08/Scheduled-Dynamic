package com.mbcoder.scheduler.constantes;

public class Constantes {

    public static final int HORA_INICIO = 9;

    public static final int MINUTO_INICIO = 0;
    public static final int HORA_FIN = 23;

    public static final int MINUTO_FIN = 0;

    public static final int POOL_SIZE = 10;

    public static final String CRON_TIME =  "*/5 * 9-20 * * MON-FRI";


    //QUERYS
    public static final String GET_BY_STATUS = "SELECT * FROM REGISTROS WHERE REGISTROS.Status LIKE %:name%";

    public static final String GET_BY_STATUS_AND_HOUR = "SELECT * FROM REGISTROS " +
            "WHERE REGISTROS.Status = 'En Proceso' " +
            "AND CAST(GETDATE() AS DATE) = CAST(REGISTROS.Dia AS DATE) " +
            "AND   REGISTROS.Hora >= :horaActual";


}

