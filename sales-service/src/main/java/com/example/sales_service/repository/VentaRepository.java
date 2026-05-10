package com.example.sales_service.repository;

import com.example.sales_service.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByClienteId(Long clienteId);
    List<Venta> findBySucursalId(Long sucursalId);
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Venta> findByEstado(String estado);
}
