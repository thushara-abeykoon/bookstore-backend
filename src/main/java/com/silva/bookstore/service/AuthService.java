package com.silva.bookstore.service;

import com.silva.bookstore.dto.UserRegisterRequestDTO;
import com.silva.bookstore.dto.AuthRequestDTO;
import com.silva.bookstore.dto.AuthResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(UserRegisterRequestDTO user);
    ResponseEntity<AuthResponseDTO> login(AuthRequestDTO user);
}
