package com.gsm.charlie.security.domain.service;

import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.payload.request.FirstUser;
import com.gsm.charlie.security.payload.request.LoginRequest;
import com.gsm.charlie.security.payload.request.SignupRequest;
import com.gsm.charlie.security.payload.request.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    User registerUser(SignupRequest signupRequest);
    ResponseEntity<?> refreshtoken(TokenRefreshRequest request);
    ResponseEntity<?> logoutUser();
    ResponseEntity<?> createRoles();
    ResponseEntity<?> firstUserAdmin(FirstUser request);
    ResponseEntity<?> emergencyUserAdmin(FirstUser request);
}
