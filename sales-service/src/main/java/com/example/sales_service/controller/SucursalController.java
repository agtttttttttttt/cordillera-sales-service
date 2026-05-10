package com.example.sales_service.controller;

import com.example.sales_service.model.Sucursal;
import com.example.sales_service.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService service;

    public SucursalController(SucursalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Sucursal>> getByRegion(@PathVariable String region) {
        return ResponseEntity.ok(service.findByRegion(region));
    }

    @PostMapping
    public ResponseEntity<Sucursal> create(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(sucursal));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Sucursal>> createBatch(@RequestBody List<Sucursal> sucursales) {
        List<Sucursal> saved = sucursales.stream()
                .map(service::save)
                .toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(service.update(id, sucursal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
