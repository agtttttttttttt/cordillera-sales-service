package com.example.inventory_service.controller;

import com.example.inventory_service.model.Inventario;
import com.example.inventory_service.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/bodega/{bodegaId}")
    public ResponseEntity<List<Inventario>> getByBodega(@PathVariable Long bodegaId) {
        return ResponseEntity.ok(service.findByBodegaId(bodegaId));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Inventario>> getByProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(service.findByProductoId(productoId));
    }

    @GetMapping("/bodega/{bodegaId}/producto/{productoId}")
    public ResponseEntity<Inventario> getStockByBodegaAndProducto(@PathVariable Long bodegaId, @PathVariable Long productoId) {
        return ResponseEntity.ok(service.findStockByBodegaAndProducto(bodegaId, productoId));
    }

    @PostMapping
    public ResponseEntity<Inventario> create(@RequestBody Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@PathVariable Long id, @RequestBody Inventario inventario) {
        return ResponseEntity.ok(service.update(id, inventario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
