package com.silva.bookstore.service;

import com.silva.bookstore.model.Book;

import java.util.List;

public interface BookService {
    public List<Book> getBooks();

    public List<Book> searchBooks(String isbn);

    public Book getBook(String isbn);

    public void addBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(String isbn);

}
