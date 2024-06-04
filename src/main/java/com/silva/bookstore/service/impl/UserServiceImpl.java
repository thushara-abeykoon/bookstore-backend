package com.silva.bookstore.service.impl;

import com.silva.bookstore.model.Book;
import com.silva.bookstore.model.User;
import com.silva.bookstore.repository.BookRepository;
import com.silva.bookstore.repository.UserRepository;
import com.silva.bookstore.service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

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

        user.setPassword(getMd5(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found"));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<String> login(User user) {
        User byUserName = userRepository.findByUsername(user.getUsername());
        if (byUserName == null)
            throw new NoSuchElementException("User not found");

        String md5Password = getMd5(user.getPassword());

        if(!Objects.equals(byUserName.getPassword(), md5Password))
            throw new AuthenticationException("Wrong Password");

        return new ResponseEntity<>("Login Success", HttpStatus.ACCEPTED);
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

    @Override
    public void likeBook(Long userId, String bookIsbn) {
        User user = userRepository.findById(userId).orElseThrow(()->new NoSuchElementException("User not found"));
        Book book = bookRepository.findById(bookIsbn).orElseThrow(()->new NoSuchElementException("Book not found"));

        user.getLikedBooks().add(book);
        book.getLikedUsers().add(user);

        userRepository.save(user);
        bookRepository.save(book);
    }

    private String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32)
                hashtext = "0" + hashtext;
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
