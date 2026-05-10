package com.example.user_service.controller;

import com.example.user_service.model.Rol;
import com.example.user_service.service.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody Rol rol) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable Long id, @RequestBody Rol rol) {
        return ResponseEntity.ok(service.update(id, rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
