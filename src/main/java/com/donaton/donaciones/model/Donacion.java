package com.donaton.donaciones.model;

import jakarta.persistence.*;

@Entity
@Table(name = "donaciones")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private int cantidad;
    private String descripcion;

    // Constructor vacío
    public Donacion() {}

    // Constructor
    public Donacion(String tipo, int cantidad, String descripcion) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() { return id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}