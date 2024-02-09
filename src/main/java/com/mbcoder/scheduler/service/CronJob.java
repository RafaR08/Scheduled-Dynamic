package com.mbcoder.scheduler.service;

import com.mbcoder.scheduler.constantes.Constantes;
import com.mbcoder.scheduler.impl.PragarmadasImpl;
import com.mbcoder.scheduler.model.Programadas;
import com.mbcoder.scheduler.repository.ProgramadasRepo;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Service
public class CronJob{
    private static Logger LOGGER = LoggerFactory.getLogger(CronJob.class);
    @Autowired
   private AddJob externalJob;

    @Autowired
    ProgramadasRepo programadasRepo;

    @Scheduled(cron = Constantes.cron)
    public void getCronDB() {

            LOGGER.info("printCron: Print every 10 seconds with cron");
            List<Programadas> list = programadasRepo.getbyStatus("Programado");
            externalJob.addJob(list);
            list.stream().peek(x->x.setStatus("En Proceso")).collect(Collectors.toList());
            programadasRepo.saveAll(list);
    }

}
