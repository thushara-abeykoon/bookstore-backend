package com.silva.bookstore.controller;

import com.silva.bookstore.dto.AuthRequestDTO;
import com.silva.bookstore.dto.AuthResponseDTO;
import com.silva.bookstore.dto.UserRegisterRequestDTO;
import com.silva.bookstore.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return authService.register(userRegisterRequestDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return authService.login(authRequestDTO);
    }
}
