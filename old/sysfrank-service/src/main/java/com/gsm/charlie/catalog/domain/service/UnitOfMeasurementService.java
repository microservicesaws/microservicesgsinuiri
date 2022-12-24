package com.gsm.charlie.catalog.domain.service;

import com.gsm.charlie.catalog.domain.model.UnitOfMeasurement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UnitOfMeasurementService {
    List<UnitOfMeasurement> getAllUnitOfMeasurements();
    UnitOfMeasurement getUnitOfMeasurementById(Long unitOfMeasurementId);
    UnitOfMeasurement createUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement);
    UnitOfMeasurement updateUnitOfMeasurement(Long unitOfMeasurementId, UnitOfMeasurement unitOfMeasurementRequest);
    ResponseEntity<?> deleteUnitOfMeasurement(Long unitOfMeasurementId);
}
