package com.api.DanielVelasco.repositories;

import com.api.DanielVelasco.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
