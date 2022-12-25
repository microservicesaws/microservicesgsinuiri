package com.gsm.charlie.inventory.resource;

import com.gsm.charlie.inventory.domain.model.Direct;
import com.gsm.charlie.inventory.domain.model.EType;
import com.gsm.charlie.sale.domain.model.InvoiceDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Getter
@Setter
public class SaveMovementResource {

    @NotNull
    private EType type;
    private String description;
    private InvoiceDetail invoiceDetail;
    private Direct direct;
    private StoreResource store;
    private ScaffoldResource scaffold;
}
