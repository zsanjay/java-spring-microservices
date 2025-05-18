package com.pm.auth_service.service;

import com.pm.auth_service.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
