package com.gsm.charlie.sale.resource;

import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class BusinessEntityResource extends AuditModel {

    private Long id;
    private String identityNumber;
    private String name;
    private String address;
}
