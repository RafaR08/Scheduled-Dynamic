package com.mbcoder.scheduler.utils;

import com.mbcoder.scheduler.entity.Programadas;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Utils {

    static LocalTime horaActual = LocalTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    public static List<Programadas> listaIntervalos(List<List<Programadas>> listaParticion) {

        List<Programadas> listCompleta = new ArrayList<>();

        listaParticion.forEach(p -> {
            horaActual = horaActual.plusSeconds(1);
            Consumer<Programadas> consumerHora = h -> h.setHora(horaActual.format(formatter));
            listCompleta.addAll(p.stream().peek(consumerHora).collect(Collectors.toList()));

        });
        return listCompleta;

    }


    public static int operacionesPorSegundo(LocalDateTime horaInicio, LocalDateTime horaFin, int listSize) {

        long diferencia = Duration.between(horaInicio, horaFin).getSeconds();
        int recordsPerSecond = (int) Math.ceil((double) listSize / diferencia);
        return recordsPerSecond;

    }



    public static String convertirAExpresionCron(Programadas convertirCron)  {

        String dateFull = new StringBuilder(convertirCron.getDia().toString()).append(" ").append(convertirCron.getHora()).toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateFull, formatter);

        String seg = String.valueOf(ldt.getSecond());
        String min = String.valueOf(ldt.getMinute());
        String hr = String.valueOf(ldt.getHour());
        String day = String.valueOf(ldt.getDayOfMonth());
        String month = String.valueOf(ldt.getMonthValue());
        String[] strArr = {seg, min, hr, day, month, "*"};
        return Stream.of(strArr).collect(Collectors.joining(" "));

    }


}

