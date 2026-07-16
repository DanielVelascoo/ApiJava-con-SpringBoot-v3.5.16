package com.api.DanielVelasco.controllers;

import com.api.DanielVelasco.dto.ClienteRequestDTO;
import com.api.DanielVelasco.dto.ClienteResponseDTO;
import com.api.DanielVelasco.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Hacemos la anotación para indicar a Spring que es un controlador
@RequestMapping("/clientes") // Anotacion de mapeo de rutas, define la ruta base del controlador.
@RequiredArgsConstructor // Las inyecciones con Autowired sirven pero es mejor hacer un constructor
// para este caso existe LOMBOK una dependencia que hace la creacion de estos constructores

public class ClienteController {
    // Inyectamos el Service
    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> obtenerClientes() {
        return clienteService.obtener();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerCliente(id);
    }

    @PostMapping
    public ClienteResponseDTO crearCliente(@Valid @RequestBody ClienteRequestDTO cliente) {
        return clienteService.crear(cliente);
    }

//    @PutMapping("/{id}")
//    public ProductoResponseDTO actualizarProducto(
//            @PathVariable Long id,
//            @RequestBody ProductoRequestDTO detalleProducto) {
//
//        return clienteService.update(id, detalleProducto);
//    }
//
//    @DeleteMapping("/{id}")
//    public String borrarProducto(@PathVariable Long id) {
//        return clienteService.borrarProducto(id);
//    }
}
