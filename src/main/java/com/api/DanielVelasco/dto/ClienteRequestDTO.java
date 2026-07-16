package com.api.DanielVelasco.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;//Importación de Jakarta para Validaciones

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @NotBlank(message = "El celular es obligatorio")
    private String celular;
}
