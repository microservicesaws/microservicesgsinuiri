package com.gsm.charlie.inventory.controller;

import com.gsm.charlie.inventory.domain.model.Scaffold;
import com.gsm.charlie.inventory.domain.service.ScaffoldService;
import com.gsm.charlie.inventory.resource.SaveScaffoldResource;
import com.gsm.charlie.inventory.resource.ScaffoldResource;
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
public class ScaffoldController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ScaffoldService scaffoldService;

    @GetMapping("/scaffolds")
    public List<ScaffoldResource> getAllScaffolds() {
        return scaffoldService.getAllScaffolds()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/scaffolds/{scaffoldId}")
    public ScaffoldResource getScaffoldById(@PathVariable(value = "scaffoldId") Long scaffoldId) {
        return convertToResource(scaffoldService.getScaffoldById(scaffoldId));
    }

    @PostMapping("/scaffolds")
    public ScaffoldResource createScaffold(
            @Valid @RequestBody SaveScaffoldResource resource) {
        Scaffold scaffold = convertToEntity(resource);
        return convertToResource(scaffoldService.createScaffold(scaffold));
    }

    @PutMapping("/scaffolds/{scaffoldId}")
    public ScaffoldResource updateScaffold(@PathVariable Long scaffoldId,
                                           @Valid @RequestBody SaveScaffoldResource resource) {
        Scaffold scaffold = convertToEntity(resource);
        return convertToResource(
                scaffoldService.updateScaffold(scaffoldId, scaffold));
    }

    @DeleteMapping("/scaffolds/{scaffoldId}")
    public ResponseEntity<?> deleteScaffold(@PathVariable Long scaffoldId) {
        return scaffoldService.deleteScaffold(scaffoldId);
    }

    private Scaffold convertToEntity(SaveScaffoldResource resource) {
        return mapper.map(resource, Scaffold.class);
    }
    private ScaffoldResource convertToResource(Scaffold entity) {
        return mapper.map(entity, ScaffoldResource.class);
    }
}
