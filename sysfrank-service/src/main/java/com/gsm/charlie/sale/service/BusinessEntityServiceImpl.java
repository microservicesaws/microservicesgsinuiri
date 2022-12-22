package com.gsm.charlie.sale.service;

import com.gsm.charlie.sale.domain.model.BusinessEntity;
import com.gsm.charlie.sale.domain.repository.BusinessEntityRepository;
import com.gsm.charlie.sale.domain.service.BusinessEntityService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessEntityServiceImpl implements BusinessEntityService {

    @Autowired
    private BusinessEntityRepository businessEntityRepository;

    @Override
    public List<BusinessEntity> getAllBusinessEntities() {
        return businessEntityRepository.findAll();
    }

    @Override
    public BusinessEntity getBusinessEntityById(Long businessEntityId) {
        return businessEntityRepository.findById(businessEntityId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "BusinessEntity", "Id", businessEntityId));
    }

    @Override
    public BusinessEntity createBusinessEntity(BusinessEntity businessEntity) {
        return businessEntityRepository.save(businessEntity);
    }

    @Override
    public BusinessEntity updateBusinessEntity(Long businessEntityId, BusinessEntity businessEntityRequest) {
        BusinessEntity businessEntity = businessEntityRepository.findById(businessEntityId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "BusinessEntity", "Id", businessEntityId));
        return businessEntityRepository.save(
                businessEntity.setIdentityNumber(businessEntityRequest.getIdentityNumber())
                        .setName(businessEntityRequest.getName())
                        .setAddress(businessEntityRequest.getAddress()));
    }

    @Override
    public ResponseEntity<?> deleteBusinessEntity(Long businessEntityId) {
        BusinessEntity businessEntity = businessEntityRepository.findById(businessEntityId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "BusinessEntity", "Id", businessEntityId));

        businessEntityRepository.delete(businessEntity);
        return ResponseEntity.ok().build();
    }
}