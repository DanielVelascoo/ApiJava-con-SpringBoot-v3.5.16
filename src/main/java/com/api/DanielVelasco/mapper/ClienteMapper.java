package com.api.DanielVelasco.mapper;

import com.api.DanielVelasco.dto.cliente.ClienteRequestDTO;
import com.api.DanielVelasco.dto.cliente.ClienteResponseDTO;
import com.api.DanielVelasco.entities.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponseDTO toDTO(Cliente cliente);

    List<ClienteResponseDTO> toDTOList(List<Cliente> clientes);

    Cliente toEntity(ClienteRequestDTO dto);
}
