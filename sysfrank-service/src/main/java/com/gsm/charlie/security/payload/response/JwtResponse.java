package com.gsm.charlie.security.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String refreshToken;
    private Long id;
    private String fullName;

    private String phone;

    private String email;
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, Long id, String fullName, String phone, String email, String username, List<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.roles = roles;
    }
}
