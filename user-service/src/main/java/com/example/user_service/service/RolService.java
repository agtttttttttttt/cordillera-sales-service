package com.example.user_service.service;

import com.example.user_service.model.Rol;
import com.example.user_service.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository repository;

    public RolService(RolRepository repository) {
        this.repository = repository;
    }

    public List<Rol> findAll() {
        return repository.findAll();
    }

    public Rol findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }

    public Rol findByNombre(String nombre) {
        return repository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con nombre: " + nombre));
    }

    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    public Rol update(Long id, Rol rol) {
        Rol existing = findById(id);
        existing.setNombre(rol.getNombre());
        existing.setDescripcion(rol.getDescripcion());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
