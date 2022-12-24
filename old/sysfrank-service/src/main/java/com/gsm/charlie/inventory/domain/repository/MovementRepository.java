package com.gsm.charlie.inventory.domain.repository;

import com.gsm.charlie.inventory.domain.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovementRepository extends JpaRepository<Movement, Long> {

}
