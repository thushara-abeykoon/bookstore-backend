package com.silva.bookstore.controller;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/registerAuthor")
    public void addNewAuthor(@RequestBody Author author) {
        authorService.addNewAuthor(author);
    }

    @GetMapping(path = "/getAuthor/{email}")
    public ResponseEntity<Author> getAuthor(@PathVariable String email) {
        Author author = authorService.getAuthor(email);
        return ResponseEntity.ok(author);
    }

}
