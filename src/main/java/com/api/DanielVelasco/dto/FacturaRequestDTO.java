package com.api.DanielVelasco.dto;
import jakarta.validation.constraints.*;//Importación de Jakarta para Validaciones
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotEmpty(message = "La factura debe tener al menos un detalle")
    private List<DetalleFacturaRequestDTO> detalles;

}
