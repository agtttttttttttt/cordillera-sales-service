package com.example.report_service.repository;

import com.example.report_service.model.RegistroKPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroKPIRepository extends JpaRepository<RegistroKPI, Long> {
    List<RegistroKPI> findByKpiId(Long kpiId);
    List<RegistroKPI> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
