package com.silva.bookstore.service;

import com.silva.bookstore.model.Author;

import java.util.List;

public interface AuthorService {
    void addNewAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthor(String email);

    void updateAuthor(Author author, Long id);

    void deleteAuthor(String email);

    boolean isAuthorExist(String email);
}
