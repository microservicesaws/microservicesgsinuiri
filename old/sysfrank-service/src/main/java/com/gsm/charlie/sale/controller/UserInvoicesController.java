package com.gsm.charlie.sale.controller;

import com.gsm.charlie.sale.domain.model.Invoice;
import com.gsm.charlie.sale.domain.service.InvoiceService;
import com.gsm.charlie.sale.resource.InvoiceResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/multiserviciosfuentes/api/v1")
public class UserInvoicesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("users/{userId}/invoices")
    public List<InvoiceResource> getAllInvoicesByUserId(@PathVariable Long userId) {
        return invoiceService.getAllInvoices()
                .stream().filter( x -> x.getUser().getId().equals(userId))
                .map(this::convertToResource)
                .collect(Collectors.toList());
    }

//    private Invoice convertToEntity(SaveInvoiceResource resource) {
//        return mapper.map(resource, Invoice.class);
//    }
    private InvoiceResource convertToResource(Invoice entity) {
        return mapper.map(entity, InvoiceResource.class);
    }
}
