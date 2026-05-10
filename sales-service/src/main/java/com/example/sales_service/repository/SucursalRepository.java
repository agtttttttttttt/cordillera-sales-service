package com.example.sales_service.repository;

import com.example.sales_service.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByRegion(String region);
    List<Sucursal> findByEstado(String estado);
}
