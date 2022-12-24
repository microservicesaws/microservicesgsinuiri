package com.gsm.charlie.security.domain.model;

import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "refreshtoken")
@Accessors(chain = true)
@Getter @Setter
@NoArgsConstructor
public class RefreshToken extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}
