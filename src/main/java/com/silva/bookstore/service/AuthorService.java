package com.silva.bookstore.service;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findAuthorByEmail(author.getEmail());
        if (authorOptional.isPresent())
            throw new IllegalStateException("email already exists");

        authorRepository.save(author);
    }

}
