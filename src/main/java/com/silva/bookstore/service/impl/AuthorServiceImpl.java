package com.silva.bookstore.service.impl;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.repository.AuthorRepository;
import com.silva.bookstore.service.AuthorService;
import com.silva.bookstore.service.util.InvalidCredentialFormatException;
import com.silva.bookstore.service.util.Validator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;
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

        if (!(  Validator.validateContactNo(author.getContactNo()) &&
                Validator.validateEmail(author.getEmail()) &&
                Validator.validateName(author.getFirstName()) &&
                Validator.validateName(author.getLastName()))){
            throw new InvalidCredentialFormatException("Invalid credential format detected");
        }


        authorRepository.save(author);
    }

    @Override
    public Author getAuthor(String email) {
        Optional<Author> authorByEmail = authorRepository.findAuthorByEmail(email);
        if (authorByEmail.isEmpty())
            throw new NoSuchElementException("unable to found author");
        else return authorByEmail.get();
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
