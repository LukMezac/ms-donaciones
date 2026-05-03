package com.donaton.donaciones.factory;

import com.donaton.donaciones.model.Donacion;

public class Ropa extends Donacion implements DonacionTipo {

    @Override
    public String getTipo() {
        return "Ropa";
    }
}