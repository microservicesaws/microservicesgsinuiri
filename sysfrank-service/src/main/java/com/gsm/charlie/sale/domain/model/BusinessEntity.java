package com.gsm.charlie.sale.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "business_entities")
@Accessors(chain = true)
@Getter @Setter
public class BusinessEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "identity_number", unique = true)
    private String identityNumber;

    @NotNull
    @Column(unique = true)
    private String name;

    private String address;

    @OneToMany(mappedBy = "businessEntity")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private List<Invoice> invoices;
}
