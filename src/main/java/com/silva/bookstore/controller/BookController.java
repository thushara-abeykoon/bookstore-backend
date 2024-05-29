package com.silva.bookstore.controller;

import com.silva.bookstore.model.Book;
import com.silva.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "registerBook")
    public void registerBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PostMapping(path = "likeBook/{isbn}")
    public void likeBook(@PathVariable String isbn) {
        bookService.likeBook(isbn);
    }

    @GetMapping(path = "getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping(path = "getBook/{bookId}")
    public Book getBook(@PathVariable String bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping(path = "searchBook/{bookId}")
    public List<Book> searchBook(@PathVariable String bookId) {
        return bookService.searchBooks(bookId);
    }

    @PutMapping(path = "updateBook")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @DeleteMapping(path = "deleteBook/{bookId}")
    public void deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
    }

}
