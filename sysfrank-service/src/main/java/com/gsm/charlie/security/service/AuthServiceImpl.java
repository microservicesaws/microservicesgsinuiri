package com.gsm.charlie.security.service;

import com.gsm.charlie.security.domain.model.ERole;
import com.gsm.charlie.security.domain.model.RefreshToken;
import com.gsm.charlie.security.domain.model.Role;
import com.gsm.charlie.security.domain.model.User;
import com.gsm.charlie.security.domain.repository.RoleRepository;
import com.gsm.charlie.security.domain.repository.UserRepository;
import com.gsm.charlie.security.domain.service.AuthService;
import com.gsm.charlie.security.exception.TokenRefreshException;
import com.gsm.charlie.security.payload.request.FirstUser;
import com.gsm.charlie.security.payload.request.LoginRequest;
import com.gsm.charlie.security.payload.request.SignupRequest;
import com.gsm.charlie.security.payload.request.TokenRefreshRequest;
import com.gsm.charlie.security.payload.response.JwtResponse;
import com.gsm.charlie.security.payload.response.MessageResponse;
import com.gsm.charlie.security.payload.response.TokenRefreshResponse;
import com.gsm.charlie.security.util.JwtCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtCenter jwtUtils;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getFullName(), userDetails.getPhone(), userDetails.getEmail(), userDetails.getUsername(), roles));
    }

    @Override
    public User registerUser(SignupRequest signUpRequest) {
        // Create new user's account
        User user = new User(signUpRequest.getFullName(), signUpRequest.getPhone(), signUpRequest.getEmail(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        System.out.println(strRoles != null);

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
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> refreshtoken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @Override
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

    @Override
    public ResponseEntity<?> createRoles() {
        if (roleRepository.findAll().isEmpty()){
            Role objRole1 = new Role();
            objRole1.setName(ERole.ROLE_ADMIN);
            Role objRole2 = new Role();
            objRole2.setName(ERole.ROLE_MODERATOR);
            Role objRole3 = new Role();
            objRole3.setName(ERole.ROLE_USER);
            roleRepository.saveAndFlush(objRole1);
            roleRepository.saveAndFlush(objRole2);
            roleRepository.save(objRole3);
            return ResponseEntity.ok(new MessageResponse("Roles create successful!"));
        }else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Roles is already taken!"));
        }
    }

    @Override
    public ResponseEntity<?> firstUserAdmin(FirstUser request) {
        if (Objects.equals(secret, request.getSecret()) &&  userRepository.findAll().isEmpty() && !roleRepository.findAll().isEmpty()){

            Set<Role> roles = new HashSet<>();
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            User user = new User();
            user.setFullName("admin");
            user.setUsername("admin");
            user.setPassword(encoder.encode("admin_2022_wow"));
            user.setRoles(roles);

            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("First user create successful!"));

        }else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Firs User is already taken!"));
        }
    }


    @Override
    public ResponseEntity<?> emergencyUserAdmin(FirstUser request) {
        if (Objects.equals(secret, request.getSecret()) && !roleRepository.findAll().isEmpty()){

            Set<Role> roles = new HashSet<>();
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            User user = new User();
            user.setFullName("admin2");
            user.setUsername("adminemergency");
            user.setPassword(encoder.encode("admin_2022"));
            user.setRoles(roles);

            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("First user create successful!"));

        }else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Firs User is already taken!"));
        }
    }
}
