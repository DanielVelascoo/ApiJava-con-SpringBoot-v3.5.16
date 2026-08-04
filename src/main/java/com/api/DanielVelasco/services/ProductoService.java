package com.api.DanielVelasco.services;


import java.util.List;


import com.api.DanielVelasco.dto.ProductoRequestDTO;
import com.api.DanielVelasco.dto.ProductoResponseDTO;
import com.api.DanielVelasco.entities.Categoria;
import com.api.DanielVelasco.entities.Producto;
import com.api.DanielVelasco.exceptions.ResourceNotFoundException;
import com.api.DanielVelasco.mapper.ProductoMapper;
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
    private final ProductoMapper productoMapper;

    //Mejoramos el método con menos lineas de código para que sea más limpio
    public List<ProductoResponseDTO> obtener() {
        return productoMapper.toDTOList(productoRepository.findAll());
    }

    //Con el mapper se simplifica más el código y es más facil de implementar
    public ProductoResponseDTO obtenerProductoById(Long id) {
        //Instanciamos el prodcuto por ID y se almacena en la variable producto
        Producto producto = productoRepository.findById(id)
                //Creamos el mensaje en caso de que no exista el producto
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return productoMapper.toDTO(producto);
    }

    //Acá usamos el método de toDTOList, la busqueda a la Entidad devuelve una List
    public List<ProductoResponseDTO> buscarPorCategoria(Long categoriaId) {
        List<Producto> productos = productoRepository.findByCategoriaId(categoriaId);
        if (productos.isEmpty()) {
            throw new ResourceNotFoundException("Categoria no encontrada");
        }
        return productoMapper.toDTOList(productos);
    }

    //Para crear es más sencillo y escalable el código
    public ProductoResponseDTO crear(ProductoRequestDTO producto) {
        // Buscar la categoría
        Categoria categoria = categoriaRepository.findById(producto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con id: " + producto.getCategoriaId()));
        // Convierte el DTO a entidad
        Producto productoNuevo = productoMapper.toEntity(producto);
        // Asigna la relación manualmente
        productoNuevo.setCategoria(categoria);
        // Guarda
        Producto productoGuardado = productoRepository.save(productoNuevo);
        // Convierte la entidad guardada a DTO de respuesta
        return productoMapper.toDTO(productoGuardado);
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