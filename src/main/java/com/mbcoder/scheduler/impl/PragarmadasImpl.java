package com.mbcoder.scheduler.impl;

import com.mbcoder.scheduler.entity.Programadas;
import com.mbcoder.scheduler.repository.ProgramadasRepo;
import com.mbcoder.scheduler.component.AddJob;
import com.mbcoder.scheduler.service.PrograServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PragarmadasImpl implements PrograServices {

    @Autowired
    ProgramadasRepo repo;

    @Autowired
    AddJob addCron;

    @Override
    public void insertarCron(List<Programadas> proList) {

        proList.stream().peek(x -> x.setStatus("En Proceso")).collect(Collectors.toList());
        addCron.addJob(proList);
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
