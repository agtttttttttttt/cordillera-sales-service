package com.example.report_service.repository;

import com.example.report_service.model.KPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KPIRepository extends JpaRepository<KPI, Long> {
    List<KPI> findByEstado(String estado);
}
