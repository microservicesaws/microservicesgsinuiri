package com.gsm.charlie.sale.controller;

import com.gsm.charlie.sale.domain.model.BusinessEntity;
import com.gsm.charlie.sale.domain.service.BusinessEntityService;
import com.gsm.charlie.sale.resource.BusinessEntityResource;
import com.gsm.charlie.sale.resource.SaveBusinessEntityResource;
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
public class BusinessEntityController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BusinessEntityService businessEntityService;

    @GetMapping("/business_entities")
    public List<BusinessEntityResource> getAllBusinessEntities() {
        return businessEntityService.getAllBusinessEntities()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/business_entities/{businessEntityId}")
    public BusinessEntityResource getBusinessEntityById(@PathVariable(value = "businessEntityId") Long businessEntityId) {
        return convertToResource(businessEntityService.getBusinessEntityById(businessEntityId));
    }

    @PostMapping("/business_entities")
    public BusinessEntityResource createBusinessEntity(
            @Valid @RequestBody SaveBusinessEntityResource resource) {
        BusinessEntity businessEntity = convertToEntity(resource);
        return convertToResource(businessEntityService.createBusinessEntity(businessEntity));
    }

    @PutMapping("/business_entities/{businessEntityId}")
    public BusinessEntityResource updateBusinessEntity(@PathVariable Long businessEntityId,
                                         @Valid @RequestBody SaveBusinessEntityResource resource) {
        BusinessEntity businessEntity = convertToEntity(resource);
        return convertToResource(
                businessEntityService.updateBusinessEntity(businessEntityId, businessEntity));
    }

    @DeleteMapping("/business_entities/{businessEntityId}")
    public ResponseEntity<?> deleteBusinessEntity(@PathVariable Long businessEntityId) {
        return businessEntityService.deleteBusinessEntity(businessEntityId);
    }

    private BusinessEntity convertToEntity(SaveBusinessEntityResource resource) {
        return mapper.map(resource, BusinessEntity.class);
    }
    private BusinessEntityResource convertToResource(BusinessEntity entity) {
        return mapper.map(entity, BusinessEntityResource.class);
    }
}