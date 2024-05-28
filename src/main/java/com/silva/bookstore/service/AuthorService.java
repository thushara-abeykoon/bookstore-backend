package com.silva.bookstore.service;

import com.silva.bookstore.model.Author;

public interface AuthorService {
    void addNewAuthor(Author author);

    Author getAuthor(int id);

    void updateAuthor(Author author);

    void deleteAuthor(int id);
}
