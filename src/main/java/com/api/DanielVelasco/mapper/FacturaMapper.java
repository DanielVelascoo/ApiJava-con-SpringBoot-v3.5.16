package com.api.DanielVelasco.mapper;


import com.api.DanielVelasco.dto.FacturaRequestDTO;
import com.api.DanielVelasco.dto.FacturaResponseDTO;
import com.api.DanielVelasco.entities.Factura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DetalleFacturaMapper.class})
public interface FacturaMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.nombre", target = "nombreCliente")
    FacturaResponseDTO toDTO(Factura factura);

    List<FacturaResponseDTO> toDTOList(List<Factura> facturas);

    Factura toEntity(FacturaRequestDTO dto);
}
