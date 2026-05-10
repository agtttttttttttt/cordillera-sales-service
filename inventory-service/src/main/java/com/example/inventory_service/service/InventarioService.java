package com.example.inventory_service.service;

import com.example.inventory_service.model.Inventario;
import com.example.inventory_service.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public List<Inventario> findAll() {
        return repository.findAll();
    }

    public Inventario findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con id: " + id));
    }

    public Inventario save(Inventario inventario) {
        return repository.save(inventario);
    }

    public Inventario update(Long id, Inventario inventario) {
        Inventario existing = findById(id);
        existing.setBodega(inventario.getBodega());
        existing.setProducto(inventario.getProducto());
        existing.setStockActual(inventario.getStockActual());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Inventario> findByBodegaId(Long bodegaId) {
        return repository.findByBodegaId(bodegaId);
    }

    public List<Inventario> findByProductoId(Long productoId) {
        return repository.findByProductoId(productoId);
    }

    public Inventario findStockByBodegaAndProducto(Long bodegaId, Long productoId) {
        return repository.findByBodegaIdAndProductoId(bodegaId, productoId)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado para bodega " + bodegaId + " y producto " + productoId));
    }
}
