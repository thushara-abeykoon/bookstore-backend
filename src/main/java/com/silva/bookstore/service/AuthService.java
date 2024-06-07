package com.silva.bookstore.service;

import com.silva.bookstore.dto.AuthRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(AuthRequestDTO user);
    ResponseEntity<String> login(AuthRequestDTO user);
}
