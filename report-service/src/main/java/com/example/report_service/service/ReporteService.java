package com.example.report_service.service;

import com.example.report_service.model.Reporte;
import com.example.report_service.repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    private final ReporteRepository repository;

    public ReporteService(ReporteRepository repository) {
        this.repository = repository;
    }

    public List<Reporte> findAll() {
        return repository.findAll();
    }

    public Reporte findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
    }

    public Reporte save(Reporte reporte) {
        return repository.save(reporte);
    }

    public Reporte update(Long id, Reporte reporte) {
        Reporte existing = findById(id);
        existing.setTitulo(reporte.getTitulo());
        existing.setDescripcion(reporte.getDescripcion());
        existing.setTipo(reporte.getTipo());
        existing.setCreadoPor(reporte.getCreadoPor());
        existing.setEstado(reporte.getEstado());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Reporte existing = findById(id);
        existing.setEstado("INACTIVO");
        repository.save(existing);
    }

    public List<Reporte> findByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public List<Reporte> findByEstado(String estado) {
        return repository.findByEstado(estado);
    }
}
