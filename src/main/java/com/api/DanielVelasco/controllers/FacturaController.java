package com.api.DanielVelasco.controllers;

import com.api.DanielVelasco.dto.ClienteRequestDTO;
import com.api.DanielVelasco.dto.ClienteResponseDTO;
import com.api.DanielVelasco.dto.FacturaRequestDTO;
import com.api.DanielVelasco.dto.FacturaResponseDTO;
import com.api.DanielVelasco.services.FacturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Hacemos la anotación para indicar a Spring que es un controlador
@RequestMapping("/facturas") // Anotacion de mapeo de rutas, define la ruta base del controlador.
@RequiredArgsConstructor // Las inyecciones con Autowired sirven pero es mejor hacer un constructor
// para este caso existe LOMBOK una dependencia que hace la creacion de estos constructores
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    public List<FacturaResponseDTO> obtenerFacturas() {
        return facturaService.obtener();
    }

    @GetMapping("/{id}")
    public FacturaResponseDTO obtenerFactura(@PathVariable Long id) {
        return facturaService.obtenerFactura(id);
    }

    @PostMapping
    public FacturaResponseDTO crearFactura(@Valid @RequestBody FacturaRequestDTO factura) {
        return facturaService.crear(factura);
    }

    @PutMapping("/{id}")
    public FacturaResponseDTO actualizarFactura(
            @PathVariable Long id,
            @RequestBody FacturaRequestDTO detalleFactura) {

        return facturaService.update(id, detalleFactura);
    }

    @DeleteMapping("/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        return facturaService.borrarFactura(id);
    }

}
