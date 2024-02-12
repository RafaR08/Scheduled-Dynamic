package com.mbcoder.scheduler.service;

import com.mbcoder.scheduler.entity.Programadas;

import java.util.List;

public interface PrograServices {

    List<Programadas> mostrar();

    List<Programadas> buscar(String nombre);

    void insertarCron(List<Programadas> proList);
}
