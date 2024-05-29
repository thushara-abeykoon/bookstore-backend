package com.silva.bookstore.service;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.model.Book;

import java.util.List;

public interface BookService {

    void likeBook(String isbn);

    List<Book> getBooks();

    List<Book> searchBooks(String isbn);

    List<Book> searchBooksByAuthor(Author author);

    Book getBook(String isbn);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(String isbn);

}
