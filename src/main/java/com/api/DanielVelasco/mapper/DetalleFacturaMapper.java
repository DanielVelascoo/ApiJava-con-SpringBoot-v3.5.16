package com.api.DanielVelasco.mapper;

import com.api.DanielVelasco.dto.detallefactura.DetalleFacturaRequestDTO;
import com.api.DanielVelasco.dto.detallefactura.DetalleFacturaResponseDTO;
import com.api.DanielVelasco.entities.DetalleFactura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleFacturaMapper {

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "producto.nombre", target = "nombreProducto")
    DetalleFacturaResponseDTO toDTO(DetalleFactura detalleFactura);

    List<DetalleFacturaResponseDTO> toDTOList(List<DetalleFactura> detalleFacturas);
    //¿Por qué ignorar producto?
    //Porque LA responsabilidad ya existe aquí:
    //Producto producto = productoRepository.findById(detalles.getProductoId())
    //y después:
    //detalle.setProducto(producto);
    @Mapping(target = "producto", ignore = true)
    DetalleFactura toEntity(DetalleFacturaRequestDTO dto);
}
