package com.api.DanielVelasco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaResponseDTO {

    private Long id;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private Long clienteId;
    private String nombreCliente;

    private List<DetalleFacturaResponseDTO> detalles;
}
