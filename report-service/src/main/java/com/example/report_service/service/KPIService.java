package com.example.report_service.service;

import com.example.report_service.model.KPI;
import com.example.report_service.repository.KPIRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KPIService {

    private final KPIRepository repository;

    public KPIService(KPIRepository repository) {
        this.repository = repository;
    }

    public List<KPI> findAll() {
        return repository.findAll();
    }

    public KPI findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI no encontrado con id: " + id));
    }

    public KPI save(KPI kpi) {
        return repository.save(kpi);
    }

    public KPI update(Long id, KPI kpi) {
        KPI existing = findById(id);
        existing.setNombre(kpi.getNombre());
        existing.setDescripcion(kpi.getDescripcion());
        existing.setFormula(kpi.getFormula());
        existing.setUnidad(kpi.getUnidad());
        existing.setMeta(kpi.getMeta());
        existing.setEstado(kpi.getEstado());
        return repository.save(existing);
    }

    public void delete(Long id) {
        KPI existing = findById(id);
        existing.setEstado("INACTIVO");
        repository.save(existing);
    }

    public List<KPI> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }
}
