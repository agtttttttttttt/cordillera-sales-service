package com.example.inventory_service.controller;

import com.example.inventory_service.model.Bodega;
import com.example.inventory_service.service.BodegaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bodegas")
public class BodegaController {

    private final BodegaService service;

    public BodegaController(BodegaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Bodega>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bodega> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Bodega>> getByRegion(@PathVariable String region) {
        return ResponseEntity.ok(service.findByRegion(region));
    }

    @PostMapping
    public ResponseEntity<Bodega> create(@RequestBody Bodega bodega) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(bodega));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bodega> update(@PathVariable Long id, @RequestBody Bodega bodega) {
        return ResponseEntity.ok(service.update(id, bodega));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
