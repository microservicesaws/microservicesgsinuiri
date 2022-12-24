package com.gsm.charlie.sale.domain.service;

import com.gsm.charlie.sale.domain.model.InvoiceDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoiceDetailService {
    List<InvoiceDetail> getAllInvoiceDetail();
    InvoiceDetail updateInvoiceDetail(Long invoiceId, Long invoiceDetailId, InvoiceDetail invoiceDetailRequest);
    ResponseEntity<?> deleteInvoiceDetail(Long invoiceId, Long invoiceDetailId);
}
