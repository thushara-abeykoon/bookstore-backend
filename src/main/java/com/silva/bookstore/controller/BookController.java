package com.silva.bookstore.controller;

import com.silva.bookstore.model.Book;
import com.silva.bookstore.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
