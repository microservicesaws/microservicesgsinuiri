package com.gsm.charlie.sale.domain.service;

import com.gsm.charlie.sale.domain.model.Invoice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Long invoiceId, Invoice invoiceRequest);
    Invoice patchInvoice(Long invoiceId, Invoice invoiceRequest);
    ResponseEntity<?> deleteInvoice(Long invoiceId);
}
