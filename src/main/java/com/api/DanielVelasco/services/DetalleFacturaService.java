package com.api.DanielVelasco.services;

import com.api.DanielVelasco.dto.DetalleFacturaRequestDTO;
import com.api.DanielVelasco.entities.DetalleFactura;
import com.api.DanielVelasco.entities.Producto;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.exceptions.StockInsuficienteException;
import com.api.DanielVelasco.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DetalleFacturaService {

    private final ProductoRepository productoRepository;

    public DetalleFactura crearDetalle(DetalleFacturaRequestDTO detalles){
        //Instanciamos el prodcuto por ID y se almacena en la variable producto
        Producto producto = productoRepository.findById(detalles.getProductoId())
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        // Validar stock disponible
        if (producto.getStock() < detalles.getCantidad()) {
            throw new StockInsuficienteException(
                    "No hay suficiente stock del producto: " + producto.getNombre()
            );
        }
        DetalleFactura detalle = new DetalleFactura();

        BigDecimal precioUnitario = BigDecimal.valueOf(producto.getPrecio());

        BigDecimal subtotal = precioUnitario
                .multiply(BigDecimal.valueOf(detalles.getCantidad()));
        // Asignar valores
        detalle.setProducto(producto);
        detalle.setCantidad(detalles.getCantidad());
        detalle.setPrecioUnitario(precioUnitario);
        detalle.setSubtotal(subtotal);
        detalle.setDescripcion(producto.getNombre());

        return detalle;

    }
}
