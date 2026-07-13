package com.api.DanielVelasco.controllers;
import java.util.List;

import com.api.DanielVelasco.dto.ProductoRequestDTO;
import com.api.DanielVelasco.dto.ProductoResponseDTO;
import com.api.DanielVelasco.services.ProductoService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Esto es todo lo que hace un controlador, luego pasaré a ver cómo funciona con un Service aparte, en ProductoService

@RestController // Hacemos la anotación para indicar a Spring que es un controlador
@RequestMapping("/productos") // Anotacion de mapeo de rutas, define la ruta base del controlador.
@RequiredArgsConstructor // Las inyecciones con Autowired sirven pero es mejor hacer un constructor
// para este caso existe LOMBOK una dependencia que hace la creacion de estos constructores
public class ProductoController {

    // Inyectamos el Service
    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponseDTO> obtenerProductos() {
        return productoService.obtener();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerProductoById(id);
    }

    @PostMapping
    public ProductoResponseDTO crearProducto(@Valid @RequestBody ProductoRequestDTO producto) {
        return productoService.crear(producto);
    }

    @PutMapping("/{id}")
    public ProductoResponseDTO actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoRequestDTO detalleProducto) {

        return productoService.update(id, detalleProducto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        return productoService.borrarProducto(id);
    }

}


// an angel sent from heaven just for me