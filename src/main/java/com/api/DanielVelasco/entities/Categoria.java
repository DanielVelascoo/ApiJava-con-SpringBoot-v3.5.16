package com.api.DanielVelasco.entities;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Se le añade la anotación Entity para indicar a Spring que es una tabla de nuestra BD
@Entity
//Con LOMBOK puedo eliminar demasiado código de los GETTERS Y SETTERS, solo añado las anotaciones que indican los mismos.
@Getter
@Setter
//Constructores a medida
@NoArgsConstructor//generará un constructor sin parámetros
@AllArgsConstructor//genera un constructor con 1 parámetro para cada campo de tu clase
public class Categoria {
    @Id //Se la añade esta anotación para que sepa que es un ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se la añade esta anotación para que se incremente automaticamente el ID
    //Creamos los atributos de esta clase Entidad, para este caso sería los campos de la tabla de nuestra BD
    private Long id; //Utilizamos long por si en algun momento son demasiados ID
    private String nombre;
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}

//Próximo a estudiar por acá, patrones como DTOs, Builders y Records