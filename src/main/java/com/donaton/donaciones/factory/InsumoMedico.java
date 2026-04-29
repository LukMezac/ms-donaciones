package com.donaton.donaciones.factory;

public class InsumoMedico implements DonacionTipo {
    @Override
    public String getTipo() {
        return "Insumo Médico";
    }
}