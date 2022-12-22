package com.gsm.charlie.inventory.controller;

import com.gsm.charlie.inventory.domain.model.Store;
import com.gsm.charlie.inventory.domain.service.StoreService;
import com.gsm.charlie.inventory.resource.SaveStoreResource;
import com.gsm.charlie.inventory.resource.StoreResource;
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
public class StoreController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StoreService storeService;

    @GetMapping("/stores")
    public List<StoreResource> getAllStores() {
        return storeService.getAllStores()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/stores/{storeId}")
    public StoreResource getStoreById(@PathVariable(value = "storeId") Long storeId) {
        return convertToResource(storeService.getStoreById(storeId));
    }

    @PostMapping("/stores")
    public StoreResource createStore(
            @Valid @RequestBody SaveStoreResource resource) {
        Store store = convertToEntity(resource);
        return convertToResource(storeService.createStore(store));
    }

    @PutMapping("/stores/{storeId}")
    public StoreResource updateStore(@PathVariable Long storeId,
                                           @Valid @RequestBody SaveStoreResource resource) {
        Store store = convertToEntity(resource);
        return convertToResource(
                storeService.updateStore(storeId, store));
    }

    @DeleteMapping("/stores/{storeId}")
    public ResponseEntity<?> deleteStore(@PathVariable Long storeId) {
        return storeService.deleteStore(storeId);
    }

    private Store convertToEntity(SaveStoreResource resource) {
        return mapper.map(resource, Store.class);
    }
    private StoreResource convertToResource(Store entity) {
        return mapper.map(entity, StoreResource.class);
    }
}
