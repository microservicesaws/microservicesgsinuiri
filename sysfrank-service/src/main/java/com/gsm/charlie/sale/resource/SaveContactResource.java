package com.gsm.charlie.sale.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Getter
@Setter
public class SaveContactResource {

    @NotNull
    @NotBlank
    private String fullName;

    private String phone;
    private String email;
    private String address;
}
