package com.gsm.charlie.sale.controller;

import com.gsm.charlie.sale.domain.model.InvoiceDetail;
import com.gsm.charlie.sale.domain.service.InvoiceDetailService;
import com.gsm.charlie.sale.resource.InvoiceDetailResource;
import com.gsm.charlie.sale.resource.SaveInvoiceDetailResource;
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
public class InvoiceDetailController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @GetMapping("/invoice_details")
    public List<InvoiceDetailResource> getAllInvoices() {
        return invoiceDetailService.getAllInvoiceDetail()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @PutMapping("/invoices/{invoiceId}/invoice_details/{invoiceDetailId}")
    public InvoiceDetailResource updateInvoiceDetail(@PathVariable Long invoiceId, @PathVariable Long invoiceDetailId,
                                         @Valid @RequestBody SaveInvoiceDetailResource resource) {
        InvoiceDetail invoiceDetail = convertToEntity(resource);
        return convertToResource(
                invoiceDetailService.updateInvoiceDetail(invoiceId, invoiceDetailId, invoiceDetail));
    }

    @DeleteMapping("/invoices/{invoiceId}/invoice_details/{invoiceDetailId}")
    public ResponseEntity<?> deleteInvoiceDetail(@PathVariable Long invoiceId, @PathVariable Long invoiceDetailId) {
        return invoiceDetailService.deleteInvoiceDetail(invoiceId, invoiceDetailId);
    }

    private InvoiceDetail convertToEntity(SaveInvoiceDetailResource resource) {
        return mapper.map(resource, InvoiceDetail.class);
    }
    private InvoiceDetailResource convertToResource(InvoiceDetail entity) {
        return mapper.map(entity, InvoiceDetailResource.class);
    }
}
