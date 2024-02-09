package com.mbcoder.scheduler.service;

import com.mbcoder.scheduler.constantes.Constantes;
import com.mbcoder.scheduler.impl.PragarmadasImpl;
import com.mbcoder.scheduler.model.Programadas;
import com.mbcoder.scheduler.repository.ProgramadasRepo;
import com.mbcoder.scheduler.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddJob implements SchedulingConfigurer {

  private static Logger LOGGER = LoggerFactory.getLogger(AddJob.class);
  private ScheduledTaskRegistrar scheduledTaskRegistrar;

  @Autowired
  ProgramadasRepo programadasRepo;


  @Bean
  public TaskScheduler poolScheduler3() {
    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler3");
    scheduler.setPoolSize(Constantes.POOL_SIZE);
    scheduler.initialize();
    return scheduler;
  }


  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    if (scheduledTaskRegistrar == null) {
      scheduledTaskRegistrar = taskRegistrar;
    }
    if (taskRegistrar.getScheduler() == null) {
      taskRegistrar.setScheduler(poolScheduler3());
    }
  }


  public void addJob(List<Programadas> myList) {
    System.out.println("myList " + myList);

    myList.forEach(x -> {

      scheduledTaskRegistrar.getScheduler().schedule(this::demo,
          t -> {
            CronTrigger trigger = new CronTrigger(Utils.convertirAExpresionCron(x));
            return trigger.nextExecutionTime(t);
          });
    });
    configureTasks(scheduledTaskRegistrar);
  }

  public void demo() {
    System.out.println("### Metodo Demo ###");
  }

}


