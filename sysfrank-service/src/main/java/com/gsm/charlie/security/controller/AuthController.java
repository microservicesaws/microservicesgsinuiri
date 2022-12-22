package com.gsm.charlie.security.controller;

import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.domain.service.AuthService;
import com.gsm.charlie.security.payload.request.FirstUser;
import com.gsm.charlie.security.payload.request.LoginRequest;
import com.gsm.charlie.security.payload.request.SignupRequest;
import com.gsm.charlie.security.payload.request.TokenRefreshRequest;
import com.gsm.charlie.security.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/multiserviciosfuentes/api/v1/auth")
public class AuthController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AuthService authService;

    @PostMapping("/sign_in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sign_up")
    public UserResource registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return convertToResource(authService.registerUser(signUpRequest));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return authService.refreshtoken(request);
    }

    @PostMapping("/sign_out")
    public ResponseEntity<?> logoutUser() {
        return authService.logoutUser();
    }

    @Transactional
    @PostMapping("/create_roles")
    public ResponseEntity<?> createRoles() {
        return authService.createRoles();
    }

    @PostMapping("/first_user")
    public ResponseEntity<?> firstUserAdmin(@Valid @RequestBody FirstUser request) {
        return authService.firstUserAdmin(request);
    }

    @PostMapping("/emergency_user")
    public ResponseEntity<?> emergencyUserAdmin(@Valid @RequestBody FirstUser request) {
        return authService.emergencyUserAdmin(request);
    }

    private User convertToEntity(SignupRequest resource) {
        return mapper.map(resource, User.class);
    }
    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }

}