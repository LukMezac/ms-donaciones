package com.donaton.donaciones.controller;

import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import org.springframework.http.ResponseEntity;
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

    // ✅ LISTAR
    @GetMapping
    public List<Donacion> listar() {
        return service.listar();
    }

    // ✅ OBTENER POR ID (ESTO FALTABA)
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Donacion d = service.buscarPorId(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(d);
    }

    // ✅ CREAR
    @PostMapping
    public Donacion crear(@RequestBody Donacion d) {
        return service.guardar(d);
    }

    // ✅ ACTUALIZAR (PARA STOCK)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Donacion d) {
        Donacion actual = service.buscarPorId(id);
        if (actual == null) {
            return ResponseEntity.notFound().build();
        }

        actual.setCantidad(d.getCantidad()); // 🔥 importante
        return ResponseEntity.ok(service.guardar(actual));
    }

    // ✅ ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Donacion actual = service.buscarPorId(id);
        if (actual == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}