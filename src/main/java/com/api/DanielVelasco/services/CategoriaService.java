package com.api.DanielVelasco.services;

import com.api.DanielVelasco.dto.CategoriaRequestDTO;
import com.api.DanielVelasco.dto.CategoriaResponseDTO;
import com.api.DanielVelasco.entities.Categoria;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> obtener() {
    //Creación de la Lista de categorias
    List<Categoria> categorias = categoriaRepository.findAll();
    //Creación del Array todo queda en la variable de respuesta
    List<CategoriaResponseDTO> respuesta = new ArrayList<>();
    //Recorremos o iteramos los objetos para llenar el Array
    for (Categoria categoria : categorias) {

        CategoriaResponseDTO datos = new CategoriaResponseDTO();
        //Pasamos los datos al dto
        datos.setId(categoria.getId());
        datos.setNombre(categoria.getNombre());
        //Adccionamos los datos a respuesta
        respuesta.add(datos);
    }
    //Retornamos la respuesta que este caso es el array con los objetos
    return respuesta;
    }

    public CategoriaResponseDTO crear(CategoriaRequestDTO categoria) {
        //Creación de la Entidad
        Categoria categorianueva = new Categoria();

        //Pasar los datos del DTO a la entidad
        categorianueva.setNombre(categoria.getNombre());

        //Guardar
        Categoria categoriaGuardada = categoriaRepository.save(categorianueva);

        //Creación de la respuesta
        CategoriaResponseDTO respuesta = new CategoriaResponseDTO();
        respuesta.setId(categoriaGuardada.getId());
        respuesta.setNombre(categoriaGuardada.getNombre());

        return respuesta;
    }

    public String borrarCategoria(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                //Creamos el mensaje en caso de que no exista la categoria
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        categoriaRepository.delete(categoria);
        //Retornamos el mensaje de exito
        return "Categoria eliminada correctamente";
    }
}
