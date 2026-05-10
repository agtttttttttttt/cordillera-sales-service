package com.example.inventory_service.repository;

import com.example.inventory_service.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByBodegaId(Long bodegaId);
    List<Inventario> findByProductoId(Long productoId);
    Optional<Inventario> findByBodegaIdAndProductoId(Long bodegaId, Long productoId);
}
