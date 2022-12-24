package com.gsm.charlie.inventory.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "scaffolds")
@Accessors(chain = true)
@Getter
@Setter
public class Scaffold extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "scaffold")
    @JsonIgnore
    private List<Movement> movements;
}
