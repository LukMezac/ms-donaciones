package com.donaton.donaciones.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate; // Importante para la fecha

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;
    private Integer cantidad;
    private String origen;
    private LocalDate fecha;
    @Column(name = "centro_acopio")
    private String centroAcopio;
}