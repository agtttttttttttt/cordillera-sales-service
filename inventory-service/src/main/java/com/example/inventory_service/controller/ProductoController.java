package com.example.inventory_service.controller;

import com.example.inventory_service.model.Producto;
import com.example.inventory_service.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Producto> getByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(service.findByCodigo(codigo));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> getByCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.findByCategoria(categoria));
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(service.update(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
