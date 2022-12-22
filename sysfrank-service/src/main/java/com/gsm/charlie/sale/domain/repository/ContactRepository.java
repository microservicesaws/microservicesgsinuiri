package com.gsm.charlie.sale.domain.repository;

import com.gsm.charlie.sale.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Long> {
}
