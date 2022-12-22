package com.gsm.charlie.catalog.resource;

import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class UnitOfMeasurementResource extends AuditModel {
    private Long id;
    private String name;
}
