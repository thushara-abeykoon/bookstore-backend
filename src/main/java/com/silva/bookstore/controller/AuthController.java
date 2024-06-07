package com.silva.bookstore.controller;

import com.silva.bookstore.dto.UserDTO;
import com.silva.bookstore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }
}
