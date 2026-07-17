package com.api.DanielVelasco.dto;
import jakarta.validation.constraints.*;//Importación de Jakarta para Validaciones
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRequestDTO {

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
    private BigDecimal total;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;
}
