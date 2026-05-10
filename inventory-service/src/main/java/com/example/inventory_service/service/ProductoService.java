package com.example.inventory_service.service;

import com.example.inventory_service.model.Producto;
import com.example.inventory_service.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> findAll() {
        return repository.findAll();
    }

    public Producto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public Producto findByCodigo(String codigo) {
        return repository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con codigo: " + codigo));
    }

    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    public Producto update(Long id, Producto producto) {
        Producto existing = findById(id);
        existing.setCodigo(producto.getCodigo());
        existing.setNombre(producto.getNombre());
        existing.setDescripcion(producto.getDescripcion());
        existing.setPrecio(producto.getPrecio());
        existing.setCategoria(producto.getCategoria());
        existing.setEstado(producto.getEstado());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Producto existing = findById(id);
        existing.setEstado("INACTIVO");
        repository.save(existing);
    }

    public List<Producto> findByCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }
}
