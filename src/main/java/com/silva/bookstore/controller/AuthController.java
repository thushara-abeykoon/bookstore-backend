package com.silva.bookstore.controller;

import com.silva.bookstore.dto.UserRequestDTO;
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
    public ResponseEntity<String> register(@RequestBody UserRequestDTO userRequestDTO) {
        return authService.register(userRequestDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDTO userRequestDTO) {
        return authService.login(userRequestDTO);
    }
}
