package com.gsm.charlie.sale.resource;

import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class ContactResource extends AuditModel {

    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
}
