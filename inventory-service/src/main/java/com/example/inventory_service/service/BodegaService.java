package com.example.inventory_service.service;

import com.example.inventory_service.model.Bodega;
import com.example.inventory_service.repository.BodegaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodegaService {

    private final BodegaRepository repository;

    public BodegaService(BodegaRepository repository) {
        this.repository = repository;
    }

    public List<Bodega> findAll() {
        return repository.findAll();
    }

    public Bodega findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));
    }

    public Bodega save(Bodega bodega) {
        return repository.save(bodega);
    }

    public Bodega update(Long id, Bodega bodega) {
        Bodega existing = findById(id);
        existing.setNombre(bodega.getNombre());
        existing.setDireccion(bodega.getDireccion());
        existing.setRegion(bodega.getRegion());
        existing.setEstado(bodega.getEstado());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Bodega existing = findById(id);
        existing.setEstado("INACTIVO");
        repository.save(existing);
    }

    public List<Bodega> findByRegion(String region) {
        return repository.findByRegion(region);
    }
}
