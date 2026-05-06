package com.donaton.donacion.service;

import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.repository.DonacionRepository;
import com.donaton.donaciones.service.DonacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DonacionServiceTest {

    @Mock
    private DonacionRepository repository;

    @InjectMocks
    private DonacionService service;

    @Test
    void testGuardarDonacion() {
        Donacion nuevaDonacion = new Donacion();
        nuevaDonacion.setNombre("Arroz");
        nuevaDonacion.setCategoria("alimento");
        nuevaDonacion.setCantidad(10);

        when(repository.save(any(Donacion.class))).thenReturn(nuevaDonacion);

        Donacion resultado = service.guardar(nuevaDonacion);

        assertNotNull(resultado);
        assertEquals("Arroz", resultado.getNombre());
        assertEquals("alimento", resultado.getCategoria());
        assertEquals(Integer.valueOf(10), resultado.getCantidad());
        verify(repository, times(1)).save(nuevaDonacion);
    }

    @Test
    void testGuardarDonacionConCategoriaInvalidaLanzaExcepcion() {
        Donacion nuevaDonacion = new Donacion();
        nuevaDonacion.setCategoria("electronica");

        assertThrows(IllegalArgumentException.class, () -> service.guardar(nuevaDonacion));
        verify(repository, never()).save(any());
    }

    @Test
    void testListar() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertNotNull(service.listar());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarPorIdExistente() {
        Donacion donacion = new Donacion();
        donacion.setNombre("Leche");
        when(repository.findById(1L)).thenReturn(Optional.of(donacion));

        Donacion resultado = service.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals("Leche", resultado.getNombre());
    }

    @Test
    void testBuscarPorIdNoExistente() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        Donacion resultado = service.buscarPorId(999L);
        assertNull(resultado);
    }

    @Test
    void testEliminar() {
        doNothing().when(repository).deleteById(7L);

        service.eliminar(7L);
        verify(repository, times(1)).deleteById(7L);
    }
}
