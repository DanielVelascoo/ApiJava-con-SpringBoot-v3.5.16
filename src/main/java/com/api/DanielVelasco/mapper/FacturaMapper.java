package com.api.DanielVelasco.mapper;


import com.api.DanielVelasco.dto.factura.FacturaRequestDTO;
import com.api.DanielVelasco.dto.factura.FacturaResponseDTO;
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

    //¿Por qué ignorar cliente?
    //Porque LA responsabilidad ya existe aquí:
    //Cliente cliente = clienteRepository.findById(factura.getClienteId())
    //y después:
    //facturaNueva.setCliente(cliente);
    @Mapping(target = "cliente", ignore = true)
    Factura toEntity(FacturaRequestDTO dto);
}
