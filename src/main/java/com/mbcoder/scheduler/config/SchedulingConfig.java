package com.mbcoder.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration//permite usar anotaciones para la inyeccion de dependencias
//para la creacion de @beans o instancias
public class SchedulingConfig {
//SchedulingConfig es una clase en la cual tendremos metodos que se crearan instnacias que spring
    //debe guardar en su contexto


    @Bean// devuelve una instancia para que se guarde en su contexto
    public ScheduledTaskRegistrar scheduledTaskRegistrar() {
        ScheduledTaskRegistrar taskRegistrar = new ScheduledTaskRegistrar();
        return taskRegistrar;
    }


}
