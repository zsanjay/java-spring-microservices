package com.pm.auth_service.service.impl;

import com.pm.auth_service.dto.LoginRequestDTO;
import com.pm.auth_service.service.AuthService;
import com.pm.auth_service.service.UserService;
import com.pm.auth_service.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserService userService,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        return userService
                .findByEmail(loginRequestDTO.email())
                .filter(user -> passwordEncoder.matches(loginRequestDTO.password(), user.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));
    }

    @Override
    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
