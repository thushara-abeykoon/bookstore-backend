package com.silva.bookstore.service.impl;

import com.silva.bookstore.model.Book;
import com.silva.bookstore.model.User;
import com.silva.bookstore.repository.BookRepository;
import com.silva.bookstore.repository.UserRepository;
import com.silva.bookstore.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addUser(User user) {

        User byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername != null)
            throw new IllegalStateException("User already exists");

        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found"));
    }

    @Override
    public void updateUser(User user) {
        User byUsername = userRepository.findByUsername(user.getUsername());

        if (byUsername != null && !Objects.equals(byUsername.getId(), user.getId()))
            throw new IllegalStateException("User already exists");

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User byUsername = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found"));
        userRepository.delete(byUsername);
    }
}
