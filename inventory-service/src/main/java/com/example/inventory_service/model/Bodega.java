package com.example.inventory_service.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bodega")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 100)
    private String region;

    @Column(length = 20)
    private String estado = "ACTIVO";

    @OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL)
    private List<Inventario> inventarios;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<Inventario> getInventarios() { return inventarios; }
    public void setInventarios(List<Inventario> inventarios) { this.inventarios = inventarios; }
}
