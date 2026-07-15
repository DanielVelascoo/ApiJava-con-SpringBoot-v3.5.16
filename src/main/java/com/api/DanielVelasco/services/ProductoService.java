package com.api.DanielVelasco.services;

import java.util.ArrayList;
import java.util.List;


import com.api.DanielVelasco.dto.ProductoRequestDTO;
import com.api.DanielVelasco.dto.ProductoResponseDTO;
import com.api.DanielVelasco.entities.Categoria;
import com.api.DanielVelasco.entities.Producto;
import com.api.DanielVelasco.exceptions.ProductoNoEncontradoException;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.repositories.CategoriaRepository;
import com.api.DanielVelasco.repositories.ProductoRepository;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

//Para el Constructor de los metodos se puede hacer más corto con el  uso
//de la anotacción de @Builder pero cómo son 2 atributos es facil, pero al menos con 8 sirve usar
//Lo usaré en otro proyecto más grande
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;


    public List<ProductoResponseDTO> obtener() {
        //Creación de la Lista en productos
        List<Producto> productos = productoRepository.findAll();
        //Creación del Array todo queda en la variable de respeusta
        List<ProductoResponseDTO> respuesta = new ArrayList<>();
        //Recorremos o iteramos los objetos para llenar el Array
        for (Producto producto : productos) {

            ProductoResponseDTO datos = new ProductoResponseDTO();
            //Pasamos los datos al dto
            datos.setId(producto.getId());
            datos.setNombre(producto.getNombre());
            //Adccionamos los datos a respuesta
            respuesta.add(datos);
        }
        //Retornamos la respuesta que este caso es el array con los objetos
        return respuesta;
    }

    public ProductoResponseDTO obtenerProductoById(Long id) {
        //Instanciamos el prodcuto por ID y se almacena en la variable producto
        Producto producto = productoRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        //Se hace el Objeto del dto
        ProductoResponseDTO dto = new ProductoResponseDTO();
        //Se le instancian datos
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        //Se retorna el dto para mostrar los datos
        return dto;

    }

    public ProductoResponseDTO crear(ProductoRequestDTO producto) {
        // Buscar la categoría
        Categoria categoria = categoriaRepository.findById(producto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con id: " + producto.getCategoriaId()));
        //CReación de la Entidad
        Producto productonuevo = new Producto();

        //Pasar los datos del DTO a la entidad
        productonuevo.setNombre(producto.getNombre());
        productonuevo.setDescripcion(producto.getDescripcion());
        productonuevo.setPrecio(producto.getPrecio());
        productonuevo.setStock(producto.getStock());

        //Guardar
        Producto productoGuardado = productoRepository.save(productonuevo);

        //Creación de la respuesta
        ProductoResponseDTO respuesta = new ProductoResponseDTO();
        respuesta.setId(productoGuardado.getId());
        respuesta.setNombre(productoGuardado.getNombre());

        return respuesta;
    }

    public ProductoResponseDTO update(Long id, ProductoRequestDTO detalleProducto) {

        Producto producto = productoRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        //Seteamos los datos enviados para actualizar
        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());
        //Guardamos los datos
        productoRepository.save(producto);
        //Llenamos el dto
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        //Retornamos la respuesta
        return dto;
    }

    public String borrarProducto(Long id) {

        Producto producto = productoRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        //Eliminamos el prdocuto y pasamos el producto erncontrado
        productoRepository.delete(producto);
        //Retornamos el mensaje de exito
        return "Producto eliminado correctamente";
    }
}