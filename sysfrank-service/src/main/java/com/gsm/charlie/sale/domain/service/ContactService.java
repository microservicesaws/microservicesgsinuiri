package com.gsm.charlie.sale.domain.service;

import com.gsm.charlie.sale.domain.model.Contact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();
    Contact getContactById(Long contactId);
    Contact createContact(Contact contact);
    Contact updateContact(Long contactId, Contact contactRequest);
    ResponseEntity<?> deleteContact(Long contactId);
}
