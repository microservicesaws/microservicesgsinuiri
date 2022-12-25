package com.gsm.charlie.inventory.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.catalog.domain.model.Product;
import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "directs")
@Accessors(chain = true)
@Getter
@Setter
public class Direct extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @NotNull
    private Integer quantity;

    @Min(0)
    @Column(precision = 12, scale = 3)
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movement_id")
    @JsonIgnore
    private Movement movement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User User;
}
