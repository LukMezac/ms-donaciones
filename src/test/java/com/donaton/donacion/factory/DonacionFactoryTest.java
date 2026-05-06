package com.donaton.donacion.factory;

import com.donaton.donaciones.factory.*;
import com.donaton.donaciones.model.Donacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DonacionFactoryTest {

    @Test
    void testCrearAlimento() {
        Donacion d = DonacionFactory.crear("alimentos");
        assertInstanceOf(Alimento.class, d);
        assertEquals("Alimento", ((DonacionTipo) d).getTipo());
    }

    @Test
    void testCrearRopa() {
        Donacion d = DonacionFactory.crear("ropa");
        assertInstanceOf(Ropa.class, d);
        assertEquals("Ropa", ((DonacionTipo) d).getTipo());
    }

    @Test
    void testCrearInsumoMedicoConTildeYEspacios() {
        Donacion d = DonacionFactory.crear("  ínsumos médicos  ");
        assertInstanceOf(InsumoMedico.class, d);
        assertEquals("InsumoMedico", ((DonacionTipo) d).getTipo());
    }

    @Test
    void testCrearHigiene() {
        Donacion d = DonacionFactory.crear("higiene");
        assertInstanceOf(Higiene.class, d);
        assertEquals("Higiene", ((DonacionTipo) d).getTipo());
    }

    @Test
    void testCrearCategoriaNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> DonacionFactory.crear(null));
    }

    @Test
    void testCrearCategoriaInvalidaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> DonacionFactory.crear("electronica"));
    }
}
