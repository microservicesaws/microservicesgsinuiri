package com.gsm.charlie.sale.domain.service;

import com.gsm.charlie.sale.domain.model.BusinessEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BusinessEntityService {
    List<BusinessEntity> getAllBusinessEntities();
    BusinessEntity getBusinessEntityById(Long businessEntityId);
    BusinessEntity createBusinessEntity(BusinessEntity businessEntity);
    BusinessEntity updateBusinessEntity(Long businessEntityId, BusinessEntity businessEntityRequest);
    ResponseEntity<?> deleteBusinessEntity(Long businessEntityId);
}
