package com.gsm.charlie.sale.resource;

import com.gsm.charlie.catalog.resource.ProductResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Accessors(chain = true)
@Getter
@Setter
public class SaveInvoiceDetailResource {

    @NotNull
    private String description;

    @Min(1)
    @NotNull
    private Integer quantity;

    private BigDecimal price;

    @Min(0)
    private Integer quantityOffer;

    @Min(0)
    @Max(100)
    private Float discount;

    private ProductResource product;
}
