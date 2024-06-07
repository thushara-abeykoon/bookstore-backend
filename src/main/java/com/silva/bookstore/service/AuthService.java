package com.silva.bookstore.service;

import com.silva.bookstore.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(UserRequestDTO user);
    ResponseEntity<String> login(UserRequestDTO user);
}
