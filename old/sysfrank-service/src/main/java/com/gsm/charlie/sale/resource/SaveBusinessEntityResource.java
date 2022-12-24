package com.gsm.charlie.sale.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Getter
@Setter
public class SaveBusinessEntityResource {

    @NotNull
    private String identityNumber;

    @NotNull
    private String name;

    private String address;
}
