package com.gsm.charlie.catalog.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.shared.domain.model.AuditModel;
import com.gsm.charlie.shared.domain.model.ETypeCurrency;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Accessors(chain = true)
@Getter
@Setter
public class Product extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String code;

    @NotNull
    @Column(unique = true)
    private String name;

    @Min(0)
    @Column(precision = 12, scale = 3)
    private BigDecimal price;

    @Min(0)
    @Column(precision = 12, scale = 3)
    private BigDecimal priceSale;

    @Column(name = "path_image")
    private String pathImage;

    @Enumerated(value = EnumType.STRING)
    private ETypeCurrency typeCurrency;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "unit_of_measurement_id")
    @JsonIgnore
    private UnitOfMeasurement unitOfMeasurement;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private List<Nickname> nicknames;
}
