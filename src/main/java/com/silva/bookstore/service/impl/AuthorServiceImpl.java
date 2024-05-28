package com.silva.bookstore.service.impl;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.repository.AuthorRepository;
import com.silva.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addNewAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findAuthorByEmail(author.getEmail());
        if (authorOptional.isPresent())
            throw new IllegalStateException("email already exists");



        authorRepository.save(author);
    }

    @Override
    public Author getAuthor(int id) {
        return null;
    }

    @Override
    public void updateAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(int id) {

    }
}
