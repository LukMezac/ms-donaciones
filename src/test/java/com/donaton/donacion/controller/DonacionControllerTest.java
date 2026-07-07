package com.donaton.donacion.controller;

import com.donaton.donaciones.controller.DonacionController;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 🔥 SOLUCIÓN: Usamos WebMvcTest que es ligero y no levanta BD
@WebMvcTest(DonacionController.class)
class DonacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Usamos @MockitoBean para que no intente buscar componentes de Spring
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
                .andExpect(jsonPath("$[0].nombre").value("Arroz"));
    }

    // ... (Mantén el resto de tus tests igual, este enfoque funcionará para todos)

    @Test
    void testCrear() throws Exception {
        when(service.guardar(any(Donacion.class))).thenReturn(donacionPrueba);

        mockMvc.perform(post("/donaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(donacionPrueba)))
                .andExpect(status().isOk());
    }
}