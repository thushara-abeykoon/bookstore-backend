package com.silva.bookstore.service.impl;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.repository.AuthorRepository;
import com.silva.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Long addNewAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findAuthorByEmail(author.getEmail());
        if (authorOptional.isPresent())
            throw new IllegalStateException("email already exists");

        authorRepository.save(author);
        return author.getId();
    }

    @Override
    public Author getAuthor(String email) {
        return null;
    }

    @Override
    public void updateAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(String email) {
        Optional<Author> authorOptional = authorRepository.findAuthorByEmail(email);
        authorOptional.ifPresent(authorRepository::delete);
    }
}
