package com.gsm.charlie.sale.service;

import com.gsm.charlie.sale.domain.model.Contact;
import com.gsm.charlie.sale.domain.repository.ContactRepository;
import com.gsm.charlie.sale.domain.service.ContactService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contact", "Id", contactId));
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long contactId, Contact contactRequest) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contact", "Id", contactId));
        return contactRepository.save(
                contact.setFullName(contactRequest.getFullName())
                        .setPhone(contactRequest.getPhone())
                        .setEmail(contactRequest.getEmail())
                        .setAddress(contactRequest.getAddress()));
    }

    @Override
    public ResponseEntity<?> deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contact", "Id", contactId));

        contactRepository.delete(contact);
        return ResponseEntity.ok().build();
    }
}