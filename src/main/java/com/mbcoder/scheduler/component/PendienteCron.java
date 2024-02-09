package com.mbcoder.scheduler.component;

import com.mbcoder.scheduler.repository.ProgramadasRepo;
import com.mbcoder.scheduler.service.AddJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PendienteCron {

    private static Logger LOGGER = LoggerFactory.getLogger(PendienteCron.class);

    @Autowired
    private AddJob externalJob;

    @Autowired
    ProgramadasRepo programadasRepo;


    @PostConstruct
    public void init() {
       // System.out.println("desde Post Construct");
//        LocalTime horaActual = LocalTime.now();
//
//        if(horaActual.isAfter(LocalTime.of(9,0)) && horaActual.isBefore(LocalTime.of(20,0)) ){
//            System.out.println("dentro del horario");
//            List<Programadas> list = programadasRepo.getbyStatusAndDate();
//            int segundosIniciales = horaActual.toSecondOfDay();
//            AtomicTime atomic = new AtomicTime(horaActual);
//
//            if (!list.isEmpty()) {
//
//                System.out.println("Tareas Perdidas");
//                list.forEach(x -> System.out.println("id: " + x.getRegistroId() + " hora: "+x.getHora()));
//                //try{
//                   // Thread.sleep(3000);
//                    list.stream()
//                            //.peek(x-> x.setHora("16:10:40"))
//                            .collect(Collectors.toList())
//                            .forEach(x -> {
//                                x.setHora(atomic.getNextSecond() );
//                                atomic.increment() ;
//
//                            });
//
//                System.out.println("Actualizacion de registros Perdidos");
//
//                 list.forEach(x -> System.out.println("id: " + x.getRegistroId() + " hora: "+x.getHora()));
//                //System.out.println("id: " + x.getRegistroId() + " hora: "+x.getHora());
//                externalJob.addJob(list);
//                    // .collect(Collectors.toList());
//
//
//
//            } else {
//               // LOGGER.info("No se encontraron tareas pendientes");
//                System.out.println("No se encontraron tareas pendientes");
//            }
//        }else {
//            System.out.println("La ejecución esta fuera de rango");
//        }


    }



}
