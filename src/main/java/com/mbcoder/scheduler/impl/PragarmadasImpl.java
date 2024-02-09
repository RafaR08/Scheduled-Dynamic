package com.mbcoder.scheduler.impl;

import com.mbcoder.scheduler.model.Programadas;
import com.mbcoder.scheduler.repository.ProgramadasRepo;
import com.mbcoder.scheduler.service.AddJob;
import com.mbcoder.scheduler.service.PrograServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PragarmadasImpl implements PrograServices {

    @Autowired
    ProgramadasRepo repo;

    @Autowired
    AddJob externalJob;

    @Override
    public void insertarCron(List<Programadas> proList) {
        //antes de mandarlo a guardar cambia status en proceso
        proList.stream().peek(x -> x.setStatus("En Proceso")).collect(Collectors.toList());
        externalJob.addJob(proList);
        repo.saveAll(proList);
    }






    @Override
    public List<Programadas> mostrar() {
        return repo.findAll();
    }

    @Override
    public List<Programadas> buscar(String nombre) {
        List<Programadas> n = null;
        n= repo.getbyStatus(nombre);
        return n;
    }



}
