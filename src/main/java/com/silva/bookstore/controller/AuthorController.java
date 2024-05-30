package com.silva.bookstore.controller;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> addNewAuthor(@RequestBody Author author) {
        authorService.addNewAuthor(author);
        return new ResponseEntity<>("registration successful", HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAll")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(path = "/get/{email}")
    public ResponseEntity<Author> getAuthor(@PathVariable String email) {
        Author author = authorService.getAuthor(email);
        return ResponseEntity.ok(author);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author, @PathVariable long id) {
        authorService.updateAuthor(author, id);
        return new ResponseEntity<>("update successful", HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{email}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String email) {
        authorService.deleteAuthor(email);
        return new ResponseEntity<>("deletion successful", HttpStatus.OK);
    }
}
