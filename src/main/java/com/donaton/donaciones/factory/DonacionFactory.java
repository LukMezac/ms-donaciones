package com.donaton.donaciones.factory;

import com.donaton.donaciones.model.Donacion;

import java.text.Normalizer;

public class DonacionFactory {

    public static Donacion crear(String categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException("Categoría no puede ser null");
        }

        //NORMALIZAR TEXTO (quita tildes y espacios raros)
        String cat = Normalizer.normalize(categoria, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase()
                .trim();

        switch (cat) {

            case "alimentos":
            case "alimento":
                return new Alimento();

            case "ropa":
                return new Ropa();

            case "insumos medicos":
            case "insumos":
            case "insumo":
                return new InsumoMedico();

            case "higiene":
                return new Higiene();

            default:
                throw new IllegalArgumentException("Categoría inválida: " + categoria);
        }
    }
}