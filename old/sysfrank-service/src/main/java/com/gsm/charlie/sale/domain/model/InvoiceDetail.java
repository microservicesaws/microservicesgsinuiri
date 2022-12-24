package com.gsm.charlie.sale.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.catalog.domain.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "invoice_details")
@Accessors(chain = true)
@Getter @Setter
public class InvoiceDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @Min(1)
    @NotNull
    private Integer quantity;

    @Min(0)
    private Integer quantityOffer;

    @Min(0)
    @Max(100)
    private Float discount;

    @NotNull
    @Column(precision = 12, scale = 3)
    private BigDecimal price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @JsonIgnore
    public BigDecimal calcRode(){
        BigDecimal result = price.multiply(new BigDecimal(quantity));
        if (discount != null)
            result = result.subtract(result.multiply(new BigDecimal(discount/100)));

        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
