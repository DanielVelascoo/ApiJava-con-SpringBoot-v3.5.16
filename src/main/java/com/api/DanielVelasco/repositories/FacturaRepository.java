package com.api.DanielVelasco.repositories;

import com.api.DanielVelasco.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
