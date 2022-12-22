package com.gsm.charlie.sale.service;

import com.gsm.charlie.inventory.domain.model.EType;
import com.gsm.charlie.sale.domain.model.ETypeVoucher;
import com.gsm.charlie.sale.domain.model.Invoice;
import com.gsm.charlie.sale.domain.repository.InvoiceRepository;
import com.gsm.charlie.sale.domain.service.InvoiceService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService  {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Transactional
    @Override
    public Invoice createInvoice(Invoice invoice) {
        var all = invoiceRepository.findAll();

        if (invoice.getMovement() == null){
            var quotations = all.stream().filter(x -> x.getNumberQuotation() != null).toList();
            if (quotations.size()>0){
                var lastIndex = quotations.stream().max(Comparator.comparing(Invoice::getNumberQuotation)).get();
                invoice.setNumberQuotation(lastIndex.getNumberQuotation() + 1);
            }else invoice.setNumberQuotation(1L);
        }else {
            if (invoice.getMovement().getType() == EType.TYPE_INPUT){
                var purchaseOrders = all.stream().filter(x -> x.getNumberPurchaseOrder() != null).toList();
                if (purchaseOrders.size()>0){
                    var lastIndex = purchaseOrders.stream().max(Comparator.comparing(Invoice::getNumberPurchaseOrder)).get();
                    invoice.setNumberPurchaseOrder(lastIndex.getNumberPurchaseOrder() + 1);
                }else invoice.setNumberPurchaseOrder(1L);
            } else {
                var saleOrders = all.stream().filter(x -> x.getNumberSaleOrder() != null).toList();
                if (saleOrders.size()>0){
                    var lastIndex = saleOrders.stream().max(Comparator.comparing(Invoice::getNumberSaleOrder)).get();
                    invoice.setNumberSaleOrder(lastIndex.getNumberSaleOrder() + 1);
                }else invoice.setNumberSaleOrder(1L);
            }
        }

        if (invoice.getTypeVoucher() == ETypeVoucher.TYPE_INVOICE){
            invoice.setNumberBill(null)
                    .setNumberProforma(null);
        }else if (invoice.getTypeVoucher() == ETypeVoucher.TYPE_BILL){
            invoice.setNumberInvoice(null)
                    .setNumberProforma(null);
        }else if (invoice.getTypeVoucher() == ETypeVoucher.TYPE_PROFORMA){
            invoice.setNumberInvoice(null)
                    .setNumberBill(null);
        }

        invoice.setTotal(invoice.calcSubTotal());
        invoice.setTotalTotal(invoice.calcTotal());
        return invoiceRepository.save(invoice);
    }

    @Transactional
    @Override
    public Invoice updateInvoice(Long invoiceId, Invoice invoiceRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invoice", "Id", invoiceId));

        invoice.getInvoiceDetails().clear();
        invoice.getInvoiceDetails().addAll(invoiceRequest.getInvoiceDetails());

        invoice.setNumberInvoice(null)
                .setNumberBill(null)
                .setNumberProforma(null);

        if (invoiceRequest.getTypeVoucher() == ETypeVoucher.TYPE_INVOICE){
            invoice.setNumberInvoice(invoiceRequest.getNumberInvoice());
        }else if (invoiceRequest.getTypeVoucher() == ETypeVoucher.TYPE_BILL){
            invoice.setNumberBill(invoiceRequest.getNumberBill());
        }else if (invoiceRequest.getTypeVoucher() == ETypeVoucher.TYPE_PROFORMA){
            invoice.setNumberProforma(invoiceRequest.getNumberProforma());
        }

        invoice.setTotal(invoice.calcSubTotal());
        invoice.setTotalTotal(invoice.calcTotal());
        return invoiceRepository.save(
                invoice.setTypeVoucher(invoiceRequest.getTypeVoucher())
                        .setNumberPurchaseOrder(invoiceRequest.getNumberPurchaseOrder())
                        .setDateVoucher(invoiceRequest.getDateVoucher())
                        .setBusinessEntity(invoiceRequest.getBusinessEntity())
                        .setContact(invoiceRequest.getContact())
                        .setNumberSeries(invoiceRequest.getNumberSeries())
                        .setValueDollar(invoiceRequest.getValueDollar()));
    }

    @Transactional
    @Override
    public Invoice patchInvoice(Long invoiceId, Invoice invoiceRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invoice", "Id", invoiceId));

        var all = invoiceRepository.findAll();

        if (invoiceRequest.getMovement() != null) {
            if (invoiceRequest.getMovement().getType() == EType.TYPE_INPUT){
                var purchaseOrders = all.stream().filter(x -> x.getNumberPurchaseOrder() != null).toList();
                if (purchaseOrders.size()>0){
                    var lastIndex = purchaseOrders.stream().max(Comparator.comparing(Invoice::getNumberPurchaseOrder)).get();
                    invoice.setNumberSaleOrder(lastIndex.getNumberPurchaseOrder() + 1);
                }else invoice.setNumberPurchaseOrder(1L);
            } else {
                var saleOrders = all.stream().filter(x -> x.getNumberSaleOrder() != null).toList();
                if (saleOrders.size()>0){
                    var lastIndex = saleOrders.stream().max(Comparator.comparing(Invoice::getNumberSaleOrder)).get();
                    invoice.setNumberSaleOrder(lastIndex.getNumberSaleOrder() + 1);
                }else invoice.setNumberSaleOrder(1L);
            }
        }else{
            invoice.setNumberPurchaseOrder(null);
            invoice.setNumberSaleOrder(null);
        }

        if (invoiceRequest.getTypeVoucher() != null)
        {
            switch (invoiceRequest.getTypeVoucher()) {
                case TYPE_INVOICE -> {
                    invoice.setNumberInvoice(invoiceRequest.getNumberInvoice());
                }
                case TYPE_BILL -> {
                    invoice.setNumberBill(invoiceRequest.getNumberBill());
                }
                default -> {
                    invoice.setNumberProforma(invoiceRequest.getNumberProforma());
                }
            }
        }else {
            invoice.setNumberInvoice(null);
            invoice.setNumberBill(null);
            invoice.setNumberProforma(null);
            invoice.setNumberSeries(null);
        }

        invoice.setTypeVoucher(invoiceRequest.getTypeVoucher());
        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setMovement(invoiceRequest.getMovement());
        invoice.setIgv(invoiceRequest.getIgv());
        invoice.setNumberSeries(invoiceRequest.getNumberSeries());

        invoice.setTotal(invoice.calcSubTotal());
        invoice.setTotalTotal(invoice.calcTotal());
        return invoiceRepository.save(invoice);
    }

    @Override
    public ResponseEntity<?> deleteInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invoice", "Id", invoiceId));

        invoiceRepository.delete(invoice);
        return ResponseEntity.ok().build();
    }

}
