package com.api.DanielVelasco.controllers;

import com.api.DanielVelasco.dto.CategoriaRequestDTO;
import com.api.DanielVelasco.dto.CategoriaResponseDTO;
import com.api.DanielVelasco.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Hacemos la anotación para indicar a Spring que es un controlador
@RequestMapping("/categorias") // Anotacion de mapeo de rutas, define la ruta base del controlador.
@RequiredArgsConstructor // Las inyecciones con Autowired sirven pero es mejor hacer un constructor
// para este caso existe LOMBOK una dependencia que hace la creacion de estos constructores
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDTO> obtenerCategorias() {
        return categoriaService.obtener();
    }

    @PostMapping
    public CategoriaResponseDTO crearCategoria(@Valid @RequestBody CategoriaRequestDTO categoria) {
        return categoriaService.crear(categoria);
    }

    @DeleteMapping
    public String eliminarCategoria(@PathVariable Long id){
        return categoriaService.borrarCategoria(id);
    }

}
