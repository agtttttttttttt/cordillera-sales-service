package com.example.report_service.service;

import com.example.report_service.model.RegistroKPI;
import com.example.report_service.repository.RegistroKPIRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroKPIService {

    private final RegistroKPIRepository repository;

    public RegistroKPIService(RegistroKPIRepository repository) {
        this.repository = repository;
    }

    public List<RegistroKPI> findAll() {
        return repository.findAll();
    }

    public RegistroKPI findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("RegistroKPI no encontrado con id: " + id));
    }

    public RegistroKPI save(RegistroKPI registro) {
        return repository.save(registro);
    }

    public List<RegistroKPI> findByKpiId(Long kpiId) {
        return repository.findByKpiId(kpiId);
    }

    public List<RegistroKPI> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin) {
        return repository.findByFechaBetween(inicio, fin);
    }
}
