package com.gsm.charlie.security.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Accessors(chain = true)
@Getter @Setter
public class SaveUserResource {

    @NotBlank
    @Size(max = 120)
    private String fullName;

    @Size(max = 50)
    private String phone;

    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private boolean isActive;

    private Set<String> roles;
}
