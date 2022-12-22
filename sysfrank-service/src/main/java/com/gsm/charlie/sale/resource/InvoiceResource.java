package com.gsm.charlie.sale.resource;

import com.gsm.charlie.sale.domain.model.EStatus;
import com.gsm.charlie.sale.domain.model.EType;
import com.gsm.charlie.sale.domain.model.ETypeVoucher;
import com.gsm.charlie.security.resource.UserResource;
import com.gsm.charlie.shared.domain.model.AuditModel;
import com.gsm.charlie.shared.domain.model.ETypeCurrency;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class InvoiceResource extends AuditModel {

    private Long id;
    private Date dateVoucher;
    private Long numberInvoice;
    private Long numberBill;
    private Long numberProforma;
    private Long numberQuotation;
    private Long numberPurchaseOrder;
    private Long numberSaleOrder;
    private BigDecimal total;
    private BigDecimal totalTotal;
    private EType type;
    private ETypeVoucher typeVoucher;
    private EStatus status;
    private Float igv;
    private ETypeCurrency typeCurrency;
    private BigDecimal valueDollar;
    private String numberSeries;

    private BusinessEntityResource businessEntity;
    private ContactResource contact;
    private UserResource user;
    private List<InvoiceDetailResource> invoiceDetails;
}
