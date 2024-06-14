package com.silva.bookstore.service.impl;

import com.silva.bookstore.dto.UserRegisterRequestDTO;
import com.silva.bookstore.dto.AuthRequestDTO;
import com.silva.bookstore.dto.AuthResponseDTO;
import com.silva.bookstore.model.UserEntity;
import com.silva.bookstore.repository.UserRepository;
import com.silva.bookstore.security.JWTService;
import com.silva.bookstore.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.silva.bookstore.security.UserRoles.USER;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<String> register(UserRegisterRequestDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername()))
            return new ResponseEntity<>("Username already exits", HttpStatus.BAD_REQUEST);



        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());

        userRepository.save(user);

        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> login(AuthRequestDTO user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDTO(token, "User authenticated!"), HttpStatus.OK);
    }
}
