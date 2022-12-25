package com.gsm.charlie.inventory.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.sale.domain.model.Invoice;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movements")
@Accessors(chain = true)
@Getter
@Setter
public class Movement extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private EType type;

    private String description;

    @OneToOne(mappedBy = "movement")
    @JsonIgnore
    private Invoice invoice;

    @OneToOne(mappedBy = "movement")
    @JsonIgnore
    private Direct direct;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @ManyToOne
    @JoinColumn(name = "scaffold_id")
    @JsonIgnore
    private Scaffold scaffold;
}
