package com.mbcoder.scheduler.service;


import com.mbcoder.scheduler.constantes.Constantes;
import com.mbcoder.scheduler.model.Programadas;
import com.mbcoder.scheduler.repository.ProgramadasRepo;
import com.mbcoder.scheduler.utils.Utils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class ServiceScheduler implements SchedulingConfigurer {

    @Autowired
    ProgramadasRepo programadasRepo;

    @Autowired
    private AddJob externalJob;


    static LocalTime horaActual = LocalTime.now();

    Calendar calendar = Calendar.getInstance();//obtener la instancia

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        LocalDateTime inicio = LocalDateTime.now().withHour(13).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime fin = LocalDateTime.now().withHour(13).withMinute(0).withSecond(9).withNano(0);

          if (horaActual.isAfter(LocalTime.of(Constantes.HORA_INICIO, 0)) && horaActual.isBefore(LocalTime.of(Constantes.HORA_FIN, 59))) {

            System.out.println("Registros Reprogramados " + "Hora Actual: " + horaActual);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            List<Programadas> list = programadasRepo.getbyStatusAndHour(timeFormatter.format(horaActual));

            if (!list.isEmpty()) {

                int recordsPerSecond = Utils.operacionesPorSegundo(inicio, fin, list.size());

                List<List<Programadas>> programadasPorSegundo = ListUtils.partition(list, recordsPerSecond);


                List<Programadas>  listaIntervalos =  Utils.listaIntervalos(programadasPorSegundo);

                externalJob.addJob(listaIntervalos);


             //  Utils.procesarJob(programadasPorSegundo, externalJob::addJob);



            } else System.out.println("No se encontraron tareas pendientes");
        } else {
            System.out.println("La ejecución esta fuera de rango");
        }

    }
}





