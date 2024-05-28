package com.silva.bookstore.service;

import com.silva.bookstore.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    List<Book> searchBooks(String isbn);

    Book getBook(String isbn);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(String isbn);

}
