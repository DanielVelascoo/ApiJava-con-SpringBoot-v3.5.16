package com.api.DanielVelasco.mapper;

import com.api.DanielVelasco.dto.ProductoRequestDTO;
import com.api.DanielVelasco.dto.ProductoResponseDTO;
import com.api.DanielVelasco.entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {


    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "nombreCategoria")
    ProductoResponseDTO toDTO(Producto producto);


    List<ProductoResponseDTO> toDTOList(List<Producto> productos);

    Producto toEntity(ProductoRequestDTO dto);

}
