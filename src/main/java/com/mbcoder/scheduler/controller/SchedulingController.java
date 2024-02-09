package com.mbcoder.scheduler.controller;

import com.mbcoder.scheduler.impl.PragarmadasImpl;
import com.mbcoder.scheduler.model.Programadas;
import com.mbcoder.scheduler.repository.ProgramadasRepo;
import com.mbcoder.scheduler.service.CronJob;
import com.mbcoder.scheduler.service.AddJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("fechas/")
public class SchedulingController {


    @Autowired
    PragarmadasImpl impl;



    @PostMapping("/addJobs")
    public  ResponseEntity<String> ejecutarCrones( @RequestBody List<Programadas> programadas){
        impl.insertarCron(programadas);
        return new ResponseEntity<>("Objetos Registrados Succes", HttpStatus.CREATED);
    }


    @GetMapping("mostrar")
    ResponseEntity<List<Programadas>> mostrar(){
        List<Programadas> fechas = impl.mostrar();

        return new ResponseEntity<List<Programadas>>(fechas, HttpStatus.OK);
    }


    @GetMapping("nombre/{name}")
    ResponseEntity <List<Programadas>> buscar(@PathVariable String name) {
        List<Programadas> prod = impl.buscar(name);
        return new ResponseEntity<List<Programadas>>(prod, HttpStatus.OK);
    }







}
