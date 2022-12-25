package com.gsm.charlie.inventory.resource;

import com.gsm.charlie.catalog.resource.ProductResource;
import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Getter
@Setter
public class DirectResource extends AuditModel {
    private Long id;
    private Integer quantity;
    private BigDecimal price;
    private ProductResource product;
    private User user;
}
