package com.gsm.charlie.sale.service;

import com.gsm.charlie.sale.domain.model.Invoice;
import com.gsm.charlie.sale.domain.model.InvoiceDetail;
import com.gsm.charlie.sale.domain.repository.InvoiceDetailRepository;
import com.gsm.charlie.sale.domain.repository.InvoiceRepository;
import com.gsm.charlie.sale.domain.service.InvoiceDetailService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDetail> getAllInvoiceDetail() {
        return invoiceDetailRepository.findAll();
    }

    @Transactional
    @Override
    public InvoiceDetail updateInvoiceDetail(Long invoiceId, Long invoiceDetailId, InvoiceDetail invoiceDetailRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invoice", "Id", invoiceId));

        InvoiceDetail invoiceDetail = invoiceDetailRepository.findById(invoiceDetailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "InvoiceDetail", "Id", invoiceDetailId));

        InvoiceDetail saveInvoiceDetail = invoiceDetailRepository.saveAndFlush(
                invoiceDetail.setDescription(invoiceDetailRequest.getDescription())
                        .setPrice(invoiceDetailRequest.getPrice())
                        .setQuantity(invoiceDetailRequest.getQuantity())
                        .setQuantityOffer(invoiceDetailRequest.getQuantityOffer())
                        .setDiscount(invoiceDetailRequest.getDiscount()));

//        invoice.setTotal(invoice.getTotalInvoiceDetails());
        invoiceRepository.save(invoice);

        return saveInvoiceDetail;
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteInvoiceDetail(Long invoiceId, Long invoiceDetailId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invoice", "Id", invoiceId));

        InvoiceDetail invoiceDetail = invoiceDetailRepository.findById(invoiceDetailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "InvoiceDetail", "Id", invoiceDetailId));

        invoiceDetailRepository.delete(invoiceDetail);
        invoiceDetailRepository.flush();

        if (invoice.getInvoiceDetails().size() <= 0){
            invoiceRepository.delete(invoice);
            return ResponseEntity.ok().build();
        }

//        invoice.setTotal(invoice.getTotalInvoiceDetails());
        invoiceRepository.save(invoice);

        return ResponseEntity.ok().build();
    }
}