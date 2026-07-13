package com.api.DanielVelasco.repositories;

import com.api.DanielVelasco.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
