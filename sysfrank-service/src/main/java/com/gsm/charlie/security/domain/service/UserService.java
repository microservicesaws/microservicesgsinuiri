package com.gsm.charlie.security.domain.service;

import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.resource.SaveUserResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User updateUser(Long userId, SaveUserResource signupRequest);
    ResponseEntity<?> deleteUser(Long userId);
}
