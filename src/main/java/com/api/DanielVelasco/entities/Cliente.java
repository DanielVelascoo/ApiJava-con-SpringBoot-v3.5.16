package com.api.DanielVelasco.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor//generará un constructor sin parámetros
@AllArgsConstructor//genera un constructor con 1 parámetro para cada campo de tu clase
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se la añade esta anotación para que se incremente automaticamente el ID
    //Creamos los atributos de esta clase Entidad, para este caso sería los campos de la tabla de nuestra BD
    private Long id; //Utilizamos long por si en algun momento son demasiados ID
    private String nombre;
    private String email;
    private String celular;
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;

}
