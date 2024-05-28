package com.silva.bookstore.service.impl;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.repository.AuthorRepository;
import com.silva.bookstore.service.AuthorService;
import com.silva.bookstore.service.util.InvalidCredentialFormatException;
import com.silva.bookstore.service.util.AuthorCredentialsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addNewAuthor(Author author) {
        if (isAuthorExist(author.getEmail()))
            throw new IllegalStateException("email already exists");

        AuthorCredentialsValidator validator = new AuthorCredentialsValidator(author);

        if (validator.isAuthorValid()){
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
    public void updateAuthor(Author newAuthor, Long id) {

        // check author exists for the id
        Author existingAuthor = authorRepository.findAuthorById(id).orElseThrow(() -> new NoSuchElementException("Unable to find author"));

        // if author exists, check the new credentials are valid
        AuthorCredentialsValidator validator = new AuthorCredentialsValidator(newAuthor);
        if (validator.isAuthorValid()) {
            throw new InvalidCredentialFormatException("Invalid credential format detected");
        }

        // check whether the new email already exists in the existing author
        if (!Objects.equals(existingAuthor.getEmail(), newAuthor.getEmail())){
            // if not check whether the new email exists in the database
            Optional<Author> authorOptional = authorRepository.findAuthorByEmail(newAuthor.getEmail());

            if (authorOptional.isPresent()) {
                throw new IllegalStateException("email already exists");
            }
        }

        // updating author
        existingAuthor.setEmail(newAuthor.getEmail());
        existingAuthor.setFirstName(newAuthor.getFirstName());
        existingAuthor.setLastName(newAuthor.getLastName());
        existingAuthor.setContactNo(newAuthor.getContactNo());

        authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(String email) {
        Author author = authorRepository.findAuthorByEmail(email).orElseThrow(() -> new NoSuchElementException("Unable to find author"));
        authorRepository.delete(author);
    }

    @Override
    public boolean isAuthorExist(String email) {
        return authorRepository.findAuthorByEmail(email).isPresent();
        }
}
