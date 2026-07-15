package com.api.DanielVelasco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se la añade esta anotación para que se incremente automaticamente el ID
    //Creamos los atributos de esta clase Entidad, para este caso sería los campos de la tabla de nuestra BD
    private Long id; //Utilizamos long por si en algun momento son demasiados ID
    private BigDecimal total;
    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
