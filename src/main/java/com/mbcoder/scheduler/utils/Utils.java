package com.mbcoder.scheduler.utils;

import com.mbcoder.scheduler.model.Programadas;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    static Calendar calendar = Calendar.getInstance();
    static LocalTime horaActual = LocalTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    public static List<Programadas> listaIntervalos(List<List<Programadas>> listaParticion) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String nuevaHoraStr = sdf.format(calendar.getTime());


        List<Programadas> listCompleta = new ArrayList<>();

        listaParticion.forEach(p -> {
            horaActual = horaActual.plusSeconds(1);
           // System.out.println("Registros por segundo: " + p.size());
           // h.setHora(LocalDateTime.ofInstant( calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalTime().toString());
            Consumer<Programadas> consumerHora = h -> h.setHora(horaActual.format(formatter));
            listCompleta.addAll(p.stream().peek(consumerHora).collect(Collectors.toList()));

        });
       // System.out.println("lista desde el metodo listaIntervalos  " + listCompleta.size());
        return listCompleta;

    }


//    public static List<OperacionCron> listTransformation(List<List<OperacionCron>> listPartitions) {
//        Calendar calendar = Calendar.getInstance();
//        ZoneId zoneId = calendar.getTimeZone().toZoneId();
//
//        List<OperacionCron> operacions = new ArrayList<>();
//        listPartitions.forEach(x -> {
//            calendar.add(Calendar.SECOND, 1);  //Siempre debe de ser 1 seg
//
//            Consumer<OperacionCron> consumerDateOp = op -> op.setFechaOperacion(
//                    LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate().toString());
//            Consumer<OperacionCron> consumerHourOp = op -> op.setHoraOperacion(
//                    LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalTime().toString());
//            operacions.addAll(
//                    x.stream().peek(consumerDateOp).peek(consumerHourOp).collect(Collectors.toList()));
//        });
//        return operacions;
//    }


    public static int operacionesPorSegundo(LocalDateTime horaInicio, LocalDateTime horaFin, int listSize) {

        long diferencia = Duration.between(horaInicio, horaFin).getSeconds();
        int recordsPerSecond = (int) Math.ceil((double) listSize / diferencia);
        return recordsPerSecond;
    }



    public static String convertirAExpresionCron(Programadas convertirCron)  {

//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate fecha = LocalDate.parse(convertirCron.getDia().toString(), dateFormatter);
//
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        LocalTime hora = LocalTime.parse(convertirCron.getHora(), timeFormatter);
//
//        LocalDateTime dateTime = LocalDateTime.of(fecha, hora);
//
//        DateTimeFormatter cronFormatter = DateTimeFormatter.ofPattern("ss mm HH dd MM ?");
//       // System.out.println("crn format" + cronFormatter);
//        return cronFormatter.format(dateTime);

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

    public static void procesarJob(List<List<Programadas>> programadasPorSegundo, Consumer<List<Programadas>> jobConsumer) {

        programadasPorSegundo.forEach(p -> {

            horaActual = horaActual.plusSeconds(1);
            System.out.println("tamaño sublistas: " + p.size());

            p.forEach(x -> {
                x.setHora(horaActual.format(formatter));
                System.out.println("hora " + x.getHora() + " ->id" + x.getRegistroId());
            });
            jobConsumer.accept(p); // Se llama a la función externa para procesar el trabajo

        });
    }


}

