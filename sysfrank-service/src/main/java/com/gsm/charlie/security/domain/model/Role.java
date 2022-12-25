package com.gsm.charlie.security.domain.model;

import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
@Entity
@Table(name = "roles")
@Accessors(chain = true)
@Getter @Setter
@NoArgsConstructor
public class Role extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
