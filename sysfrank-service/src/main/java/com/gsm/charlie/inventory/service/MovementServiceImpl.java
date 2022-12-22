package com.gsm.charlie.inventory.service;

import com.gsm.charlie.inventory.domain.model.Movement;
import com.gsm.charlie.inventory.domain.repository.MovementRepository;
import com.gsm.charlie.inventory.domain.service.MovementService;
import com.gsm.charlie.sale.domain.repository.InvoiceDetailRepository;
import com.gsm.charlie.sale.domain.repository.InvoiceRepository;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    @Override
    public Movement getMovementById(Long movementId) {
        return movementRepository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Movement", "Id", movementId));
    }

    @Override
    public Movement createMovement(Movement movement) {
        return movementRepository.save(movement);
    }

    @Override
    public Movement updateMovement(Long movementId, Movement movementRequest) {
        Movement movement = movementRepository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Movement", "Id", movementId));
        return movementRepository.save(
                movement.setDescription(movementRequest.getDescription()));
    }

    @Override
    public ResponseEntity<?> deleteMovement(Long movementId) {
        Movement movement = movementRepository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Movement", "Id", movementId));

        movementRepository.delete(movement);
        return ResponseEntity.ok().build();
    }
}