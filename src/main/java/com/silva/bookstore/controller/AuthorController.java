package com.silva.bookstore.controller;

import com.silva.bookstore.dto.AuthorRequestDTO;
import com.silva.bookstore.dto.AuthorResponseDTO;
import com.silva.bookstore.model.Author;
import com.silva.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path = "api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // has to update all the request bodies with author request dtos
    @PostMapping(path = "/register")
    public ResponseEntity<String> addNewAuthor(@RequestBody AuthorRequestDTO author) {
        authorService.addNewAuthor(author);
        return new ResponseEntity<>("registration successful", HttpStatus.CREATED);
    }

    @GetMapping(path = "/get-all")
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(path = "/get/{email}")
    public AuthorResponseDTO getAuthor(@PathVariable String email) {
        return authorService.getAuthor(email);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateAuthor(@RequestBody AuthorRequestDTO author, @PathVariable long id) {
        authorService.updateAuthor(author, id);
        return new ResponseEntity<>("update successful", HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{email}")
    public ResponseEntity<String> deleteAuthor(@PathVariable String email) {
        authorService.deleteAuthor(email);
        return new ResponseEntity<>("deletion successful", HttpStatus.OK);
    }
}
