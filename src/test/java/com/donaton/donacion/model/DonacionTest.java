package com.donaton.donacion.model;

import com.donaton.donaciones.model.Donacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DonacionTest {

    @Test
    void testGettersAndSetters() {
        // Creamos una donación de prueba
        Donacion donacion = new Donacion();

        // Asignamos valores (sin el ID)
        donacion.setNombre("Agua Mineral");
        donacion.setCategoria("alimento");
        donacion.setCantidad(50);

        // Verificamos que los valores se guardaron correctamente
        assertNotNull(donacion);
        assertEquals("Agua Mineral", donacion.getNombre());
        assertEquals("alimento", donacion.getCategoria());
        assertEquals(50, donacion.getCantidad());
    }
}