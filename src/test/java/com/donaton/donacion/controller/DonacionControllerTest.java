package com.donaton.donacion.controller;

import com.donaton.donaciones.MsDonacionesApplication;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Usamos SpringBootTest apuntando a la clase principal para evitar problemas de paquetes
@SpringBootTest(classes = MsDonacionesApplication.class)
@AutoConfigureMockMvc
class DonacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DonacionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListar() throws Exception {
        when(service.listar()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/donaciones"))
                .andExpect(status().isOk());
    }

    @Test
    void testCrear() throws Exception {
        Donacion d = new Donacion();
        d.setNombre("Arroz");
        when(service.guardar(any(Donacion.class))).thenReturn(d);

        mockMvc.perform(post("/donaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk());
    }
}