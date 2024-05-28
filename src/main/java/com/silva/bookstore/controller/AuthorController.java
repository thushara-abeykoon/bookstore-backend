package com.silva.bookstore.controller;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/registerAuthor")
    public void addNewAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
    }
}
