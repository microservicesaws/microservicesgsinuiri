package com.gsm.charlie.inventory.resource;

import com.gsm.charlie.catalog.resource.ProductResource;
import com.gsm.charlie.security.resource.UserResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Accessors(chain = true)
@Getter
@Setter
public class SaveDirectResource {

    @Min(1)
    @NotNull
    private Integer quantity;

    @Min(0)
    private BigDecimal price;

    private ProductResource product;
    private MovementResource movement;
    private UserResource user;
}
