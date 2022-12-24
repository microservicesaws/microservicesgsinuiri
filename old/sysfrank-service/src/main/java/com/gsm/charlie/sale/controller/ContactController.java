package com.gsm.charlie.sale.controller;

import com.gsm.charlie.sale.domain.model.Contact;
import com.gsm.charlie.sale.domain.service.ContactService;
import com.gsm.charlie.sale.resource.ContactResource;
import com.gsm.charlie.sale.resource.SaveContactResource;
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
public class ContactController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List<ContactResource> getAllContacts() {
        return contactService.getAllContacts()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/contacts/{contactId}")
    public ContactResource getContactById(@PathVariable(value = "contactId") Long contactId) {
        return convertToResource(contactService.getContactById(contactId));
    }

    @PostMapping("/contacts")
    public ContactResource createContact(
            @Valid @RequestBody SaveContactResource resource) {
        Contact contact = convertToEntity(resource);
        return convertToResource(contactService.createContact(contact));
    }

    @PutMapping("/contacts/{contactId}")
    public ContactResource updateContact(@PathVariable Long contactId,
                                         @Valid @RequestBody SaveContactResource resource) {
        Contact contact = convertToEntity(resource);
        return convertToResource(
                contactService.updateContact(contactId, contact));
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable Long contactId) {
        return contactService.deleteContact(contactId);
    }

    private Contact convertToEntity(SaveContactResource resource) {
        return mapper.map(resource, Contact.class);
    }
    private ContactResource convertToResource(Contact entity) {
        return mapper.map(entity, ContactResource.class);
    }
}