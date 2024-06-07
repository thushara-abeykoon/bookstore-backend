package com.silva.bookstore.service;

import com.silva.bookstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(UserDTO user);
    ResponseEntity<String> login(UserDTO user);
}
