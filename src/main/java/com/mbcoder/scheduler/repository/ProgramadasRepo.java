package com.mbcoder.scheduler.repository;

import com.mbcoder.scheduler.constantes.Constantes;
import com.mbcoder.scheduler.entity.Programadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ProgramadasRepo extends JpaRepository<Programadas,Integer> {


    @Query(value = Constantes.GET_BY_STATUS,nativeQuery = true)
    List<Programadas> getbyStatus(@Param("name")String name);


    @Query(value = Constantes.GET_BY_STATUS_AND_HOUR, nativeQuery = true)
    List<Programadas> getbyStatusAndHour(@Param("horaActual") String horaActual );



}
