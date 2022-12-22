package com.gsm.charlie.sale.resource;

import com.gsm.charlie.inventory.resource.MovementResource;
import com.gsm.charlie.sale.domain.model.EStatus;
import com.gsm.charlie.sale.domain.model.ETypeVoucher;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class PatchInvoiceResource {

    private String numberInvoice;
    private String numberBill;
    private String numberProforma;
    private ETypeVoucher typeVoucher;
    private EStatus status;
    private MovementResource movement;
    private Float igv;
    private String numberSeries;
}

