package com.donaton.donaciones.controller;

import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donaciones")
@CrossOrigin("*")
public class DonacionController {

    private final DonacionService service;

    public DonacionController(DonacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Donacion> listar() {
        return service.listar();
    }

    @PostMapping
    public Donacion crear(@RequestBody Donacion d) {
        return service.guardar(d);
    }
}