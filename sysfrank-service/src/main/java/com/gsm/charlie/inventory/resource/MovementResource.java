package com.gsm.charlie.inventory.resource;

import com.gsm.charlie.inventory.domain.model.EType;
import com.gsm.charlie.sale.resource.InvoiceResource;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class MovementResource extends AuditModel {

    private Long id;
    private EType type;
    private String description;
    private InvoiceResource invoice;
    private DirectResource direct;
    private StoreResource store;
    private ScaffoldResource scaffold;
}
