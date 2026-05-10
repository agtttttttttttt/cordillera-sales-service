package com.example.report_service.controller;

import com.example.report_service.model.KPI;
import com.example.report_service.service.KPIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kpis")
public class KPIController {

    private final KPIService service;

    public KPIController(KPIService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<KPI>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KPI> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<KPI> create(@RequestBody KPI kpi) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(kpi));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KPI> update(@PathVariable Long id, @RequestBody KPI kpi) {
        return ResponseEntity.ok(service.update(id, kpi));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
