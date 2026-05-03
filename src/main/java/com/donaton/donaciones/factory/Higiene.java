package com.donaton.donaciones.factory;

import com.donaton.donaciones.model.Donacion;

public class Higiene extends Donacion implements DonacionTipo {

    @Override
    public String getTipo() {
        return "Higiene";
    }
}