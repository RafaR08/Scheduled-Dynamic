package com.mbcoder.scheduler.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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



}
