package com.silva.bookstore.service;

import com.silva.bookstore.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    void addUser(User user);
    User getUser(Long id);
    ResponseEntity<String> login(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    void likeBook(Long userId, String bookIsbn);
}
