package com.example.inventory_service.service;

import com.example.inventory_service.model.Inventario;
import com.example.inventory_service.model.MovimientoInventario;
import com.example.inventory_service.repository.InventarioRepository;
import com.example.inventory_service.repository.MovimientoInventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final InventarioRepository inventarioRepository;

    public MovimientoInventarioService(MovimientoInventarioRepository movimientoRepository,
                                        InventarioRepository inventarioRepository) {
        this.movimientoRepository = movimientoRepository;
        this.inventarioRepository = inventarioRepository;
    }

    public List<MovimientoInventario> findByInventarioId(Long inventarioId) {
        return movimientoRepository.findByInventarioId(inventarioId);
    }

    @Transactional
    public MovimientoInventario register(MovimientoInventario movimiento) {
        Inventario inventario = inventarioRepository.findById(movimiento.getInventario().getId())
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        if ("ENTRADA".equalsIgnoreCase(movimiento.getTipo())) {
            inventario.setStockActual(inventario.getStockActual() + movimiento.getCantidad());
        } else if ("SALIDA".equalsIgnoreCase(movimiento.getTipo())) {
            if (inventario.getStockActual() < movimiento.getCantidad()) {
                throw new RuntimeException("Stock insuficiente en inventario");
            }
            inventario.setStockActual(inventario.getStockActual() - movimiento.getCantidad());
        } else {
            throw new RuntimeException("Tipo de movimiento invalido: " + movimiento.getTipo());
        }

        inventarioRepository.save(inventario);
        movimiento.setInventario(inventario);
        return movimientoRepository.save(movimiento);
    }
}
