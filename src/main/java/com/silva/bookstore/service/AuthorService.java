package com.silva.bookstore.service;

import com.silva.bookstore.model.Author;

public interface AuthorService {
    Long addNewAuthor(Author author);

    Author getAuthor(String email);

    void updateAuthor(Author author);

    void deleteAuthor(String email);
}
