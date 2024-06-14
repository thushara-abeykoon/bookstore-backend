package com.silva.bookstore.service;

import com.silva.bookstore.dto.BookResponseDTO;
import com.silva.bookstore.model.Book;

import java.util.List;

public interface BookService {

    List<BookResponseDTO> getBooks();

    void likeBook(Long userId, String bookIsbn);

    List<BookResponseDTO> searchBooks(String isbn);

    List<BookResponseDTO> searchBooksByAuthor(String author);

    BookResponseDTO getBook(String isbn);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(String isbn);

}
