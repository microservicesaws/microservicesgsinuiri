package com.gsm.charlie.inventory.controller;

import com.gsm.charlie.inventory.domain.model.Direct;
import com.gsm.charlie.inventory.domain.service.DirectService;
import com.gsm.charlie.inventory.resource.DirectResource;
import com.gsm.charlie.inventory.resource.SaveDirectResource;
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
public class DirectController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DirectService directService;

    @GetMapping("/directs")
    public List<DirectResource> getAllDirects() {
        return directService.getAllDirects()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/directs/{directId}")
    public DirectResource getDirectById(@PathVariable(value = "directId") Long directId) {
        return convertToResource(directService.getDirectById(directId));
    }

    @PostMapping("/directs")
    public DirectResource createDirect(
            @Valid @RequestBody SaveDirectResource resource) {
        Direct direct = convertToEntity(resource);
        return convertToResource(directService.createDirect(direct));
    }

    @PutMapping("/directs/{directId}")
    public DirectResource updateDirect(@PathVariable Long directId,
                                           @Valid @RequestBody SaveDirectResource resource) {
        Direct direct = convertToEntity(resource);
        return convertToResource(
                directService.updateDirect(directId, direct));
    }

    @DeleteMapping("/directs/{directId}")
    public ResponseEntity<?> deleteDirect(@PathVariable Long directId) {
        return directService.deleteDirect(directId);
    }

    private Direct convertToEntity(SaveDirectResource resource) {
        return mapper.map(resource, Direct.class);
    }
    private DirectResource convertToResource(Direct entity) {
        return mapper.map(entity, DirectResource.class);
    }
}
