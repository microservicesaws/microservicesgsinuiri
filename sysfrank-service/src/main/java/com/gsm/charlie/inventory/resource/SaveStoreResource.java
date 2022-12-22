package com.gsm.charlie.inventory.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Getter
@Setter
public class SaveStoreResource {

    @NotNull
    private String name;
}
