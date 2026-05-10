package com.example.sales_service.service;

import com.example.sales_service.model.Sucursal;
import com.example.sales_service.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    private final SucursalRepository repository;

    public SucursalService(SucursalRepository repository) {
        this.repository = repository;
    }

    public List<Sucursal> findAll() {
        return repository.findAll();
    }

    public Sucursal findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + id));
    }

    public Sucursal save(Sucursal sucursal) {
        return repository.save(sucursal);
    }

    public Sucursal update(Long id, Sucursal sucursal) {
        Sucursal existing = findById(id);
        existing.setNombre(sucursal.getNombre());
        existing.setDireccion(sucursal.getDireccion());
        existing.setRegion(sucursal.getRegion());
        existing.setTelefono(sucursal.getTelefono());
        existing.setEstado(sucursal.getEstado());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Sucursal existing = findById(id);
        existing.setEstado("INACTIVO");
        repository.save(existing);
    }

    public List<Sucursal> findByRegion(String region) {
        return repository.findByRegion(region);
    }
}
