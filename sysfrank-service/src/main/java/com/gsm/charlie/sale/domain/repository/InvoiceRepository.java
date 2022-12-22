package com.gsm.charlie.sale.domain.repository;

import com.gsm.charlie.sale.domain.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
