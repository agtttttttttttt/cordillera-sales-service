package com.example.inventory_service.repository;

import com.example.inventory_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByCodigo(String codigo);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByEstado(String estado);
}
