package com.gsm.charlie.security.service;

import com.gsm.charlie.security.domain.model.ERole;
import com.gsm.charlie.security.domain.model.Role;
import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.domain.repository.RoleRepository;
import com.gsm.charlie.security.domain.repository.UserRepository;
import com.gsm.charlie.security.domain.service.UserService;
import com.gsm.charlie.security.resource.SaveUserResource;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));
    }


    @Override
    public User updateUser(Long userId, SaveUserResource signUpRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "ROLE_MODERATOR" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        return userRepository.save(user
                .setFullName(signUpRequest.getFullName())
                .setPhone(signUpRequest.getPhone())
                .setEmail(signUpRequest.getEmail())
                .setActive(signUpRequest.isActive())
                .setUsername(signUpRequest.getUsername()));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
