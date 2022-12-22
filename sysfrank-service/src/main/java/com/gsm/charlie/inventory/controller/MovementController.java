package com.gsm.charlie.inventory.controller;

import com.gsm.charlie.inventory.domain.model.Movement;
import com.gsm.charlie.inventory.domain.service.MovementService;
import com.gsm.charlie.inventory.resource.MovementResource;
import com.gsm.charlie.inventory.resource.SaveMovementResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/multiserviciosfuentes/api/v1")
public class MovementController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MovementService movementService;

    @GetMapping("/movements")
    public List<MovementResource> getAllMovements() {
        return movementService.getAllMovements()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/movements/{movementId}")
    public MovementResource getMovementById(@PathVariable(value = "movementId") Long movementId) {
        return convertToResource(movementService.getMovementById(movementId));
    }

    @PostMapping("/movements")
    public MovementResource createMovement(
            @Valid @RequestBody SaveMovementResource resource) {
        Movement movement = convertToEntity(resource);
        return convertToResource(movementService.createMovement(movement));
    }

    @PutMapping("/movements/{movementId}")
    public MovementResource updateMovement(@PathVariable Long movementId,
                                         @Valid @RequestBody SaveMovementResource resource) {
        Movement movement = convertToEntity(resource);
        return convertToResource(
                movementService.updateMovement(movementId, movement));
    }

    @DeleteMapping("/movements/{movementId}")
    public ResponseEntity<?> deleteMovement(@PathVariable Long movementId) {
        return movementService.deleteMovement(movementId);
    }

    private Movement convertToEntity(SaveMovementResource resource) {
        return mapper.map(resource, Movement.class);
    }
    private MovementResource convertToResource(Movement entity) {
        return mapper.map(entity, MovementResource.class);
    }
}
