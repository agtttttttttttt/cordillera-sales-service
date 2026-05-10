package com.example.report_service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "kpi")
public class KPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 255)
    private String formula;

    @Column(length = 50)
    private String unidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal meta;

    @Column(length = 20)
    private String estado = "ACTIVO";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getFormula() { return formula; }
    public void setFormula(String formula) { this.formula = formula; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public BigDecimal getMeta() { return meta; }
    public void setMeta(BigDecimal meta) { this.meta = meta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
