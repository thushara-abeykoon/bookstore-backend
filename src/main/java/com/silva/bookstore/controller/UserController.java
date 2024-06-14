package com.silva.bookstore.controller;

import com.silva.bookstore.dto.UserRequestDTO;
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

    @GetMapping(path = "/get/by-id/{userId}")
    public UserResponseDTO getUser(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @GetMapping(path = "get/by-username/{username}")
    public UserResponseDTO getUser(@PathVariable("username") String username) {
        return userService.getByUsername(username);
    }

    @GetMapping(path = "/getAll")
    public List<UserResponseDTO> getALlUsers() {
        return userService.getAll();
    }

    @PutMapping(path = "/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId, @RequestBody UserRequestDTO user) {
        userService.updateUser(userId, user);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
