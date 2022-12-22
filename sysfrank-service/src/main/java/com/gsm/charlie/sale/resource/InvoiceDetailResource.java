package com.gsm.charlie.sale.resource;

import com.gsm.charlie.catalog.resource.ProductResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Getter
@Setter
public class InvoiceDetailResource {
    private Long id;
    private String description;
    private Integer quantity;
    private Integer quantityOffer;
    private Float discount;
    private BigDecimal price;
    private ProductResource product;
}
