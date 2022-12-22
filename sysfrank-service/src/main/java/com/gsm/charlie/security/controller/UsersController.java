package com.gsm.charlie.security.controller;

import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.domain.service.UserService;
import com.gsm.charlie.security.resource.SaveUserResource;
import com.gsm.charlie.security.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/multiserviciosfuentes/api/v1")
public class UsersController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/users")
    public List<UserResource> getAllUsers() {
        return userService.getAllUsers()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @Valid @RequestBody SaveUserResource signupRequest) {
        return convertToResource(userService.updateUser(userId, signupRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }
    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}