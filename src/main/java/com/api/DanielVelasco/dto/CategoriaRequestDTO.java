package com.api.DanielVelasco.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //La Data contiene Getters, Setters, toString, quals y hashCode.
//Se dice que cuando el proyecto es grande se debe usar cada uno por separado pero por el momento Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO {

    //VALIDACIONES de estás para los campos
    @NotBlank(message = "El nombre es obligatorio")//Definimos que el campo no debe estar vacion o con espacios
    @Size(max = 100, message = "El nombre no puede tener más de 100 Caracteres")//Limite de caracteres
    private String nombre;
}
