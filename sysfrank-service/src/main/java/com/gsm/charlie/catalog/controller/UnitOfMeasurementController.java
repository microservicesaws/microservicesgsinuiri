package com.gsm.charlie.catalog.controller;

import com.gsm.charlie.catalog.domain.model.UnitOfMeasurement;
import com.gsm.charlie.catalog.domain.service.UnitOfMeasurementService;
import com.gsm.charlie.catalog.resource.SaveUnitOfMeasurementResource;
import com.gsm.charlie.catalog.resource.UnitOfMeasurementResource;
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
public class UnitOfMeasurementController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UnitOfMeasurementService unitOfMeasurementService;

    @GetMapping("/unit_of_measurements")
    public List<UnitOfMeasurementResource> getAllUnitOfMeasurements() {
        return unitOfMeasurementService.getAllUnitOfMeasurements()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/unit_of_measurements/{unitOfMeasurementId}")
    public UnitOfMeasurementResource getUnitOfMeasurementById(@PathVariable (value = "unitOfMeasurementId") Long unitOfMeasurementId) {
        return convertToResource(unitOfMeasurementService.getUnitOfMeasurementById(unitOfMeasurementId));
    }

    @PostMapping("/unit_of_measurements")
    public UnitOfMeasurementResource createUnitOfMeasurement(
            @Valid @RequestBody SaveUnitOfMeasurementResource resource) {
        UnitOfMeasurement unitOfMeasurement = convertToEntity(resource);
        return convertToResource(unitOfMeasurementService.createUnitOfMeasurement(unitOfMeasurement));
    }

    @PutMapping("/unit_of_measurements/{unitOfMeasurementId}")
    public UnitOfMeasurementResource updateUnitOfMeasurement(@PathVariable Long unitOfMeasurementId,
                                         @Valid @RequestBody SaveUnitOfMeasurementResource resource) {
        UnitOfMeasurement unitOfMeasurement = convertToEntity(resource);
        return convertToResource(
                unitOfMeasurementService.updateUnitOfMeasurement(unitOfMeasurementId, unitOfMeasurement));
    }

    @DeleteMapping("/unit_of_measurements/{unitOfMeasurementId}")
    public ResponseEntity<?> deleteUnitOfMeasurement(@PathVariable Long unitOfMeasurementId) {
        return unitOfMeasurementService.deleteUnitOfMeasurement(unitOfMeasurementId);
    }

    private UnitOfMeasurement convertToEntity(SaveUnitOfMeasurementResource resource) {
        return mapper.map(resource, UnitOfMeasurement.class);
    }
    private UnitOfMeasurementResource convertToResource(UnitOfMeasurement entity) {
        return mapper.map(entity, UnitOfMeasurementResource.class);
    }
}