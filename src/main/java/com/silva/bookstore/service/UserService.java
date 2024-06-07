package com.silva.bookstore.service;

import com.silva.bookstore.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserResponseDTO getOne(Long userId);
    List<UserResponseDTO> getAll();
}
