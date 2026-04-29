package com.donaton.donaciones.factory;

import com.donaton.donaciones.factory.Alimento;
import com.donaton.donaciones.factory.DonacionTipo;
import com.donaton.donaciones.factory.InsumoMedico;
import com.donaton.donaciones.factory.Ropa;

public class DonacionFactory {

    public static DonacionTipo crear(String categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException("Categoría no puede ser null");
        }

        switch (categoria.toLowerCase()) {

            case "alimentos":
            case "alimento":
                return new Alimento();

            case "ropa":
                return new Ropa();

            case "insumos":
            case "insumo":
                return new InsumoMedico();

            default:
                throw new IllegalArgumentException("Categoría inválida: " + categoria);
        }
    }
}