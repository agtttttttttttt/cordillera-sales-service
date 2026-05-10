package com.example.sales_service.service;

import com.example.sales_service.model.*;
import com.example.sales_service.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository,
                        ClienteRepository clienteRepository,
                        SucursalRepository sucursalRepository,
                        ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta findById(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
    }

    @Transactional
    public Venta save(Venta venta) {
        Cliente cliente = clienteRepository.findById(venta.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Sucursal sucursal = sucursalRepository.findById(venta.getSucursal().getId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        venta.setCliente(cliente);
        venta.setSucursal(sucursal);
        venta.setFecha(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        for (DetalleVenta detalle : venta.getDetalleVentas()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())));
            total = total.add(detalle.getSubtotal());
        }
        venta.setTotal(total);
        return ventaRepository.save(venta);
    }

    public void delete(Long id) {
        Venta existing = findById(id);
        existing.setEstado("ANULADA");
        ventaRepository.save(existing);
    }

    public List<Venta> findByClienteId(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId);
    }

    public List<Venta> findBySucursalId(Long sucursalId) {
        return ventaRepository.findBySucursalId(sucursalId);
    }

    public List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaBetween(inicio, fin);
    }
}
