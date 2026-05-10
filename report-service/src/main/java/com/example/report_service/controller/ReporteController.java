package com.example.report_service.controller;

import com.example.report_service.model.Reporte;
import com.example.report_service.service.ReporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Reporte>> getByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(service.findByTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<Reporte> create(@RequestBody Reporte reporte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reporte));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> update(@PathVariable Long id, @RequestBody Reporte reporte) {
        return ResponseEntity.ok(service.update(id, reporte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
