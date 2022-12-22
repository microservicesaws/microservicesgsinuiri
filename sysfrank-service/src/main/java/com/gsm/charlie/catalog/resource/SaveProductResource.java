package com.gsm.charlie.catalog.resource;

import com.gsm.charlie.catalog.domain.model.Nickname;
import com.gsm.charlie.shared.domain.model.ETypeCurrency;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
public class SaveProductResource {
    @NotNull
    @Column(unique = true)
    private String code;

    @NotNull
    @Column(unique = true)
    private String name;

    @Min(0)
    private BigDecimal price;

    @Min(0)
    private BigDecimal priceSale;

    private String pathImage;

    @NotNull
    private UnitOfMeasurementResource unitOfMeasurement;
    private List<Nickname> nicknames;

    @Enumerated(value = EnumType.STRING)
    private ETypeCurrency typeCurrency;
}
