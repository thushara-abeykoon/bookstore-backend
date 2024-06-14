package com.silva.bookstore.controller;

import com.silva.bookstore.dto.BookResponseDTO;
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

    @PostMapping(path = "{bookIsbn}/like/{userId}")
    public ResponseEntity<String> likeBook (@PathVariable("userId") Long userId, @PathVariable("bookIsbn") String bookIsbn) {
        bookService.likeBook(userId,bookIsbn);
        return new ResponseEntity<>(String.format("User %s liked Book %s", userId, bookIsbn), HttpStatus.CREATED);
    }

    @GetMapping(path = "get-all")
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping(path = "get/{bookIsbn}")
    public BookResponseDTO getBook(@PathVariable String bookIsbn) {
        return bookService.getBook(bookIsbn);
    }

    @GetMapping(path = "search/{bookId}")
    public List<BookResponseDTO> searchBook(@PathVariable String bookId) {
        return bookService.searchBooks(bookId);
    }

    @GetMapping(path = "get/by-author/{email}")
    public List<BookResponseDTO> getByAuthor(@PathVariable String email) {
        return bookService.searchBooksByAuthor(email);
    }

    // has to update all the request bodies with book request dto
    @PutMapping(path = "update")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity<>("book updated", HttpStatus.OK);
    }

    // has to update
    @DeleteMapping(path = "delete/{bookIsbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookIsbn) {
        bookService.deleteBook(bookIsbn);
        return new ResponseEntity<>("book deleted", HttpStatus.OK);
    }

}
