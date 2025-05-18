package com.pm.auth_service.service;

import com.pm.auth_service.dto.LoginRequestDTO;

import java.util.Optional;

public interface AuthService {
    Optional<String> authenticate(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
}
