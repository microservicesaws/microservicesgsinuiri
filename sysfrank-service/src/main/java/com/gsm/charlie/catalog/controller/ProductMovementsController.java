package com.gsm.charlie.catalog.controller;

import com.gsm.charlie.catalog.domain.service.ProductService;
import com.gsm.charlie.inventory.domain.model.Movement;
import com.gsm.charlie.inventory.domain.service.MovementService;
import com.gsm.charlie.inventory.resource.MovementResource;
import com.gsm.charlie.inventory.resource.SaveMovementResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/multiserviciosfuentes/api/v1")
public class ProductMovementsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private MovementService movementService;

    @GetMapping("/products/{productId}/movements")
    public List<MovementResource> getMovementsByProductId(@PathVariable Long productId) {
        System.out.println(movementService.getAllMovements().size());
        return movementService.getAllMovements()
                .stream()
                .filter(item -> item.getDirect().getProduct().getId().equals(productId))
                .map(this::convertToResource)
                .collect(Collectors.toList());
    }

    private Movement convertToEntity(SaveMovementResource resource) {
        return mapper.map(resource, Movement.class);
    }
    private MovementResource convertToResource(Movement entity) {
        return mapper.map(entity, MovementResource.class);
    }
}
