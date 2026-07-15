package com.api.DanielVelasco.repositories;

import com.api.DanielVelasco.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
