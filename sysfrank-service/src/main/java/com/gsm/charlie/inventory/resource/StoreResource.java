package com.gsm.charlie.inventory.resource;

import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class StoreResource extends AuditModel {
    private Long id;
    private String name;
}
