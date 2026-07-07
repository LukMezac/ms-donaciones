package com.donaton.donacion.controller;

import com.donaton.donaciones.MsDonacionesApplication;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 🔥 EL CAMBIO DEFINITIVO ESTÁ AQUÍ 🔥
@SpringBootTest(classes = MsDonacionesApplication.class)
@AutoConfigureMockMvc
class DonacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DonacionService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Donacion donacionPrueba;

    @BeforeEach
    void setUp() {
        donacionPrueba = new Donacion();
        donacionPrueba.setNombre("Arroz");
        donacionPrueba.setCategoria("alimento");
        donacionPrueba.setCantidad(10);
    }

    @Test
    void testListar() throws Exception {
        List<Donacion> donaciones = Arrays.asList(donacionPrueba);
        when(service.listar()).thenReturn(donaciones);

        mockMvc.perform(get("/donaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Arroz"))
                .andExpect(jsonPath("$[0].cantidad").value(10));
    }

    @Test
    void testObtenerExistente() throws Exception {
        when(service.buscarPorId(1L)).thenReturn(donacionPrueba);

        mockMvc.perform(get("/donaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Arroz"));
    }

    @Test
    void testObtenerNoExistente() throws Exception {
        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/donaciones/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrear() throws Exception {
        when(service.guardar(any(Donacion.class))).thenReturn(donacionPrueba);

        mockMvc.perform(post("/donaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(donacionPrueba)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Arroz"));
    }

    @Test
    void testActualizarExistenteConDatos() throws Exception {
        Donacion detalles = new Donacion();
        detalles.setNombre("Fideos");
        detalles.setCantidad(20);

        when(service.buscarPorId(1L)).thenReturn(donacionPrueba);
        when(service.guardar(any(Donacion.class))).thenReturn(donacionPrueba);

        mockMvc.perform(put("/donaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detalles)))
                .andExpect(status().isOk());
    }

    @Test
    void testActualizarExistenteConCamposVacios() throws Exception {
        Donacion detallesVacios = new Donacion();

        when(service.buscarPorId(1L)).thenReturn(donacionPrueba);
        when(service.guardar(any(Donacion.class))).thenReturn(donacionPrueba);

        mockMvc.perform(put("/donaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detallesVacios)))
                .andExpect(status().isOk());
    }

    @Test
    void testActualizarNoExistente() throws Exception {
        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(put("/donaciones/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(donacionPrueba)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarExistente() throws Exception {
        when(service.buscarPorId(1L)).thenReturn(donacionPrueba);
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/donaciones/1"))
                .andExpect(status().isOk());

        verify(service, times(1)).eliminar(1L);
    }

    @Test
    void testEliminarNoExistente() throws Exception {
        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(delete("/donaciones/99"))
                .andExpect(status().isNotFound());

        verify(service, never()).eliminar(any());
    }
}