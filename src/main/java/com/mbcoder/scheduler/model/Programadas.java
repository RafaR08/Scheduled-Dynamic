package com.mbcoder.scheduler.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "REGISTROS")
public class Programadas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGISTROID")
    Integer      registroId;
    LocalDate    dia;
    String       hora;
    String       status;


    private static final long serialVersionUID = 1L;


    public Integer getRegistroId() {
        return registroId;
    }

    public void setRegistroId(Integer registroId) {
        this.registroId = registroId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }


}
