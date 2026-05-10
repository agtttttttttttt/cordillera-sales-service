package com.example.sales_service.service;

import com.example.sales_service.model.Cliente;
import com.example.sales_service.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    public Cliente findByRut(String rut) {
        return repository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con rut: " + rut));
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente existing = findById(id);
        existing.setRut(cliente.getRut());
        existing.setNombre(cliente.getNombre());
        existing.setEmail(cliente.getEmail());
        existing.setTelefono(cliente.getTelefono());
        existing.setDireccion(cliente.getDireccion());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
