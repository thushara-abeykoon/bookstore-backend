package com.silva.bookstore.service.impl;

import com.silva.bookstore.dto.UserRequestDTO;
import com.silva.bookstore.dto.UserResponseDTO;
import com.silva.bookstore.exception.ElementAlreadyExistsException;
import com.silva.bookstore.model.UserEntity;
import com.silva.bookstore.repository.UserRepository;
import com.silva.bookstore.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO getById(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User doesn't exists"));
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRole());
    }

    @Override
    public UserResponseDTO getByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new NoSuchElementException(("User doesn't exists")));
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRole());
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

    @Override
    public void updateUser(Long userId, UserRequestDTO user) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User doesn't exists"));

        if (userRepository.existsByUsername(user.getUsername()))
            throw new ElementAlreadyExistsException("Username already exists");

        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRole(user.getRole());

        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        userRepository.delete(userEntity);
    }


}
