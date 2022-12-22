package com.gsm.charlie.sale.controller;

import com.gsm.charlie.sale.domain.model.Invoice;
import com.gsm.charlie.sale.domain.service.InvoiceService;
import com.gsm.charlie.sale.resource.InvoiceResource;
import com.gsm.charlie.sale.resource.PatchInvoiceResource;
import com.gsm.charlie.sale.resource.SaveInvoiceResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/multiserviciosfuentes/api/v1")
public class InvoiceController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private InvoiceService invoiceService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/invoices")
    public List<InvoiceResource> getAllInvoices() {
        return invoiceService.getAllInvoices()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @PostMapping("/invoices")
    public InvoiceResource createInvoice(@Valid @RequestBody SaveInvoiceResource resource) {
        Invoice invoice = convertToEntity(resource);
        return convertToResource(invoiceService.createInvoice(invoice));
    }

    @PutMapping("/invoices/{invoiceId}")
    public InvoiceResource updateInvoice(@PathVariable Long invoiceId,
                                         @Valid @RequestBody SaveInvoiceResource resource) {
        Invoice invoice = convertToEntity(resource);
        return convertToResource(invoiceService.updateInvoice(invoiceId, invoice));
    }

    @PatchMapping("/invoices/{invoiceId}")
    public InvoiceResource patchInvoice(@PathVariable Long invoiceId,
                                         @Valid @RequestBody PatchInvoiceResource resource) {
        Invoice invoice = convertToEntityPatch(resource);
        return convertToResource(invoiceService.patchInvoice(invoiceId, invoice));
    }

    @DeleteMapping("/invoices/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long invoiceId) {
        return invoiceService.deleteInvoice(invoiceId);
    }

    private Invoice convertToEntityPatch(PatchInvoiceResource resource) {
        return mapper.map(resource, Invoice.class);
    }

    private Invoice convertToEntity(SaveInvoiceResource resource) {
        return mapper.map(resource, Invoice.class);
    }
    private InvoiceResource convertToResource(Invoice entity) {
        return mapper.map(entity, InvoiceResource.class);
    }
}
