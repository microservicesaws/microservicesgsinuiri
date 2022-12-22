package com.gsm.charlie.sale.resource;

import com.gsm.charlie.inventory.resource.MovementResource;
import com.gsm.charlie.sale.domain.model.EStatus;
import com.gsm.charlie.sale.domain.model.EType;
import com.gsm.charlie.sale.domain.model.ETypeVoucher;
import com.gsm.charlie.security.resource.UserResource;
import com.gsm.charlie.shared.domain.model.ETypeCurrency;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class SaveInvoiceResource {

    @NotNull
    private Date dateVoucher;
    private String numberInvoice;
    private String numberBill;
    private String numberProforma;
    private String numberQuotation;
    private String numberPurchaseOrder;
    private String numberSaleOrder;

    @NotNull
    private EType type;
    private ETypeVoucher typeVoucher;
    @NotNull
    private EStatus status;

    private BigDecimal valueDollar;
    private String numberSeries;

    private BusinessEntityResource businessEntity;
    private ContactResource contact;
    private MovementResource movement;
    private UserResource user;
    private List<InvoiceDetailResource> invoiceDetails;
    private ETypeCurrency typeCurrency;
    private Float igv;
}

