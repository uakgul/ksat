package com.example.ksat.service;

import com.example.ksat.domain.User;

public interface UserService {
    void checkUserExistOrThrow(Long userId);
    User getUserOrThrow(Long userId);
}
