package com.donaton.donaciones.service;

import com.donaton.donaciones.factory.DonacionFactory;
import com.donaton.donaciones.factory.DonacionTipo;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.repository.DonacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonacionService {

    private final DonacionRepository repository;

    public DonacionService(DonacionRepository repository) {
        this.repository = repository;
    }

    public List<Donacion> listar() {
        return repository.findAll();
    }

    public Donacion guardar(Donacion d) {

        DonacionTipo tipo = DonacionFactory.crear(d.getCategoria());


        System.out.println("Factory creó tipo: " + tipo.getTipo());

        return repository.save(d);
    }

    public Donacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}