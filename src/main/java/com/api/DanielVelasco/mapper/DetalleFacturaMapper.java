package com.api.DanielVelasco.mapper;

import com.api.DanielVelasco.dto.DetalleFacturaRequestDTO;
import com.api.DanielVelasco.dto.DetalleFacturaResponseDTO;
import com.api.DanielVelasco.entities.DetalleFactura;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleFacturaMapper {

    DetalleFacturaResponseDTO toDTO(DetalleFactura detalleFactura);

    List<DetalleFacturaResponseDTO> toDTOList(List<DetalleFactura> detalleFacturas);

    DetalleFactura toEntity(DetalleFacturaRequestDTO dto);
}
