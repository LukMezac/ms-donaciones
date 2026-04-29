package com.donaton.donaciones.factory;

public class Alimento implements DonacionTipo {
    @Override
    public String getTipo() {
        return "Alimento";
    }
}