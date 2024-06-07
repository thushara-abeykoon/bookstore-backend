package com.silva.bookstore.controller;

import com.silva.bookstore.model.Book;
import com.silva.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "register")
    public ResponseEntity<String> registerBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>("book registered", HttpStatus.CREATED);
    }

    @PostMapping(path = "{userId}/like/{bookIsbn}")
    public ResponseEntity<String> likeBook (@PathVariable("userId") Long userId, @PathVariable("bookIsbn") String bookIsbn) {
        bookService.likeBook(userId,bookIsbn);
        return new ResponseEntity<>(String.format("User %s liked Book %s", userId, bookIsbn), HttpStatus.CREATED);
    }

    @GetMapping(path = "getAll")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping(path = "get/{bookId}")
    public Book getBook(@PathVariable String bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping(path = "search/{bookId}")
    public List<Book> searchBook(@PathVariable String bookId) {
        return bookService.searchBooks(bookId);
    }

    @GetMapping(path = "getByAuthor/{email}")
    public List<Book> getByAuthor(@PathVariable String email) {
        return bookService.searchBooksByAuthor(email);
    }

    @PutMapping(path = "update")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity<>("book updated", HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("book deleted", HttpStatus.OK);
    }

}
