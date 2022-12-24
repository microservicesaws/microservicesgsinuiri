package com.gsm.charlie.inventory.domain.service;

import com.gsm.charlie.inventory.domain.model.Movement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovementService {
    List<Movement> getAllMovements();
    Movement getMovementById(Long movementId);
    Movement createMovement(Movement movement);
    Movement updateMovement(Long movementId, Movement movementRequest);
    ResponseEntity<?> deleteMovement(Long movementId);
}
