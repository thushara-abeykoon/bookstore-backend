package com.silva.bookstore.service;

import com.silva.bookstore.model.User;

public interface UserService {
    void addUser(User user);
    User getUser(Long id);
    void updateUser(User user);
    void deleteUser(Long id);
}
