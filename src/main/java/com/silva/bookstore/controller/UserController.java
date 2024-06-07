package com.silva.bookstore.controller;

import com.silva.bookstore.dto.UserResponseDTO;
import com.silva.bookstore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/get/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(userService.getOne(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public List<UserResponseDTO> getALlUsers() {
        return userService.getAll();
    }
}
