package com.gsm.charlie.security.resource;

import com.gsm.charlie.security.domain.model.Role;
import com.gsm.charlie.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Getter @Setter
public class UserResource extends AuditModel {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String username;
    private String password;
    private boolean isActive;
    private List<Role> roles;
}
