package com.example.sales_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String rut;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Column(length = 255)
    private String direccion;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro = LocalDate.now();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venta> ventas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public List<Venta> getVentas() { return ventas; }
    public void setVentas(List<Venta> ventas) { this.ventas = ventas; }
}
