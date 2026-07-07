package com.donaton.donacion.controller;

import com.donaton.donaciones.MsDonacionesApplication;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = MsDonacionesApplication.class,
        properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
)
@AutoConfigureMockMvc
class DonacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DonacionService service;

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
        d.setCategoria("Alimentos");
        d.setCantidad(50);
        d.setOrigen("Empresa X");
        d.setFecha(LocalDate.now());
        d.setCentroAcopio("Sede Central");


        when(service.guardar(any(Donacion.class))).thenReturn(d);

        mockMvc.perform(post("/donaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origen").value("Empresa X"))
                .andExpect(jsonPath("$.centroAcopio").value("Sede Central"));
    }
}