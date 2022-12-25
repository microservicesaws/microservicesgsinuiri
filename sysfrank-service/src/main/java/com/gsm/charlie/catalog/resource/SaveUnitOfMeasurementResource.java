package com.gsm.charlie.catalog.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Getter
@Setter
public class SaveUnitOfMeasurementResource {
    @NotNull
    private String name;
}
