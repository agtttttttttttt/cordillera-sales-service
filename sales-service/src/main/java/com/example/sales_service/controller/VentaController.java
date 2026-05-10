package com.example.sales_service.controller;

import com.example.sales_service.model.Venta;
import com.example.sales_service.service.VentaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Venta>> getByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.findByClienteId(clienteId));
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Venta>> getBySucursal(@PathVariable Long sucursalId) {
        return ResponseEntity.ok(service.findBySucursalId(sucursalId));
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Venta>> getByFechaBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(service.findByFechaBetween(inicio, fin));
    }

    @PostMapping
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
