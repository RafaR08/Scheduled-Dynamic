package com.mbcoder.scheduler.service;

import com.mbcoder.scheduler.constantes.Constantes;
import com.mbcoder.scheduler.model.Programadas;
import com.mbcoder.scheduler.repository.ProgramadasRepo;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
