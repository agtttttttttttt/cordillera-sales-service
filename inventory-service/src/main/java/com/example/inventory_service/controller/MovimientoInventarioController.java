package com.example.inventory_service.controller;

import com.example.inventory_service.model.MovimientoInventario;
import com.example.inventory_service.service.MovimientoInventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoInventarioController {

    private final MovimientoInventarioService service;

    public MovimientoInventarioController(MovimientoInventarioService service) {
        this.service = service;
    }

    @GetMapping("/inventario/{inventarioId}")
    public ResponseEntity<List<MovimientoInventario>> getByInventario(@PathVariable Long inventarioId) {
        return ResponseEntity.ok(service.findByInventarioId(inventarioId));
    }

    @PostMapping
    public ResponseEntity<MovimientoInventario> create(@RequestBody MovimientoInventario movimiento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(movimiento));
    }
}
