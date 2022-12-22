package com.gsm.charlie.catalog.service;

import com.gsm.charlie.catalog.domain.model.UnitOfMeasurement;
import com.gsm.charlie.catalog.domain.repository.UnitOfMeasurementRepository;
import com.gsm.charlie.catalog.domain.service.UnitOfMeasurementService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitOfMeasurementServiceImpl implements UnitOfMeasurementService {

    @Autowired
    private UnitOfMeasurementRepository unitOfMeasurementRepository;

    @Override
    public List<UnitOfMeasurement> getAllUnitOfMeasurements() {
        return unitOfMeasurementRepository.findAll();
    }

    @Override
    public UnitOfMeasurement getUnitOfMeasurementById(Long unitOfMeasurementId) {
        return unitOfMeasurementRepository.findById(unitOfMeasurementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "UnitOfMeasurement", "Id", unitOfMeasurementId));
    }

    @Override
    public UnitOfMeasurement createUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        return unitOfMeasurementRepository.save(unitOfMeasurement);
    }

    @Override
    public UnitOfMeasurement updateUnitOfMeasurement(Long unitOfMeasurementId, UnitOfMeasurement unitOfMeasurementRequest) {
        UnitOfMeasurement unitOfMeasurement = unitOfMeasurementRepository.findById(unitOfMeasurementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "UnitOfMeasurement", "Id", unitOfMeasurementId));
        return unitOfMeasurementRepository.save(
                unitOfMeasurement.setName(unitOfMeasurementRequest.getName()));
    }

    @Override
    public ResponseEntity<?> deleteUnitOfMeasurement(Long unitOfMeasurementId) {
        UnitOfMeasurement unitOfMeasurement = unitOfMeasurementRepository.findById(unitOfMeasurementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "UnitOfMeasurement", "Id", unitOfMeasurementId));

        unitOfMeasurementRepository.delete(unitOfMeasurement);
        return ResponseEntity.ok().build();
    }
}
