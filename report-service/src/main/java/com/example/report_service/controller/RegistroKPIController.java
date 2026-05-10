package com.example.report_service.controller;

import com.example.report_service.model.RegistroKPI;
import com.example.report_service.service.RegistroKPIService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroKPIController {

    private final RegistroKPIService service;

    public RegistroKPIController(RegistroKPIService service) {
        this.service = service;
    }

    @GetMapping("/kpi/{kpiId}")
    public ResponseEntity<List<RegistroKPI>> getByKpiId(@PathVariable Long kpiId) {
        return ResponseEntity.ok(service.findByKpiId(kpiId));
    }

    @GetMapping("/rango")
    public ResponseEntity<List<RegistroKPI>> getByRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(service.findByFechaBetween(inicio, fin));
    }

    @PostMapping
    public ResponseEntity<RegistroKPI> create(@RequestBody RegistroKPI registro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(registro));
    }
}
