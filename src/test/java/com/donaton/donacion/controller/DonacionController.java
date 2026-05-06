package com.donaton.donacion.controller; // Asegúrate que el paquete sea 'donaciones'

import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    private final DonacionService service;

    public DonacionController(DonacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Donacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> obtener(@PathVariable Long id) {
        Donacion donacion = service.buscarPorId(id);
        return donacion != null ? ResponseEntity.ok(donacion) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Donacion crear(@RequestBody Donacion donacion) {
        return service.guardar(donacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donacion> actualizar(@PathVariable Long id, @RequestBody Donacion detalles) {
        Donacion existente = service.buscarPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();

        // Actualizamos los campos necesarios
        if (detalles.getCantidad() != 0) existente.setCantidad(detalles.getCantidad());
        if (detalles.getNombre() != null) existente.setNombre(detalles.getNombre());

        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Donacion existente = service.buscarPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();

        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}