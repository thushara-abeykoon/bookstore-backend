package com.silva.bookstore.service.impl;

import com.silva.bookstore.dto.UserResponseDTO;
import com.silva.bookstore.model.UserEntity;
import com.silva.bookstore.repository.UserRepository;
import com.silva.bookstore.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO getOne(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User doesn't exists"));
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRole(user.getRole());

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getAll() {
        List<UserEntity> allUsers = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();

        allUsers.forEach(user -> {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setRole(user.getRole());

            userResponseDTOList.add(userResponseDTO);
        });


        return userResponseDTOList;
    }
}
