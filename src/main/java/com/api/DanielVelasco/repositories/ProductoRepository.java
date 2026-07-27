package com.api.DanielVelasco.repositories;

import com.api.DanielVelasco.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //Podemos usar Consultas derivadas por nombre de método
    List<Producto> findByCategoriaId(Long categoriaId); //Así se arma con Spring Boot
}
