package com.api.DanielVelasco.services;

import com.api.DanielVelasco.dto.DetalleFacturaRequestDTO;
import com.api.DanielVelasco.dto.DetalleFacturaResponseDTO;
import com.api.DanielVelasco.entities.DetalleFactura;
import com.api.DanielVelasco.entities.Producto;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.exceptions.StockInsuficienteException;
import com.api.DanielVelasco.mapper.DetalleFacturaMapper;
import com.api.DanielVelasco.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DetalleFacturaService {

    private final ProductoRepository productoRepository;
    private final DetalleFacturaMapper detalleFacturaMapper;

    public DetalleFactura crearDetalle(DetalleFacturaRequestDTO detalles) {
        // Buscar el producto por ID
        Producto producto = productoRepository.findById(detalles.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        // Validar stock disponible
        if (producto.getStock() < detalles.getCantidad()) {
            throw new StockInsuficienteException(
                    "No hay suficiente stock del producto: " + producto.getNombre()
            );
        }
        // Convertir DTO a entidad
        DetalleFactura detalle = detalleFacturaMapper.toEntity(detalles);
        // Completar información que el DTO no posee
        detalle.setProducto(producto);
        // Calcular precio y subtotal
        BigDecimal precioUnitario = BigDecimal.valueOf(producto.getPrecio());
        BigDecimal subtotal = precioUnitario.multiply(
                BigDecimal.valueOf(detalles.getCantidad())
        );
        detalle.setPrecioUnitario(precioUnitario);
        detalle.setSubtotal(subtotal);
        return detalle;
    }
}
