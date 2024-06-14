package com.silva.bookstore.service;

import com.silva.bookstore.dto.UserRequestDTO;
import com.silva.bookstore.dto.UserResponseDTO;
import java.util.List;

public interface UserService {
    UserResponseDTO getById(Long userId);
    UserResponseDTO getByUsername(String username);
    List<UserResponseDTO> getAll();
    void updateUser(Long userId, UserRequestDTO user);
    void deleteUser(Long userId);
}
