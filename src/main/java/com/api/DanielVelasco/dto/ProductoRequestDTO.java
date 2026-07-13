package com.api.DanielVelasco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;//Importación de Jakarta para Validaciones


@Data //La Data contiene Getters, Setters, toString, quals y hashCode.
//Se dice que cuando el proyecto es grande se debe usar cada uno por separado pero por el momento Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {
    //VALIDACIONES de estás para los campos
    @NotBlank(message = "El nombre es obligatorio")//Definimos que el campo no debe estar vacion o con espacios
    @Size(max = 100, message = "El nombre no puede tener más de 100 Caracteres")//Limite de caracteres
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @Positive(message = "El numero debe ser mayor a 0, ceroz")//Solo números positivos mayores a cero
    @NotNull(message = "El precio es obligatorio")//Dato no NULL
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")//Número positivos y mayores a cero
    private int stock;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;

}