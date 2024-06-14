package com.silva.bookstore.service.impl;

import com.silva.bookstore.dto.AuthorRequestDTO;
import com.silva.bookstore.dto.AuthorResponseDTO;
import com.silva.bookstore.model.Author;
import com.silva.bookstore.model.Book;
import com.silva.bookstore.repository.AuthorRepository;
import com.silva.bookstore.repository.BookRepository;
import com.silva.bookstore.service.AuthorService;
import com.silva.bookstore.exception.InvalidCredentialFormatException;
import com.silva.bookstore.service.util.AuthorCredentialsValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addNewAuthor(AuthorRequestDTO authorRequestDTO) {

        Author author = new Author();
        author.setFirstName(authorRequestDTO.getFirstName());
        author.setLastName(authorRequestDTO.getLastName());
        author.setEmail(authorRequestDTO.getEmail());
        author.setContactNo(author.getContactNo());

        if (isAuthorExist(author.getEmail()))
            throw new IllegalStateException("email already exists");

        AuthorCredentialsValidator validator = new AuthorCredentialsValidator(author);

        if (validator.isAuthorValid()){
            throw new InvalidCredentialFormatException("Invalid credential format detected");
        }


        authorRepository.save(author);
    }

    @Override
    public AuthorResponseDTO getAuthor(String email) {
        Author authorByEmail = authorRepository.findAuthorByEmail(email).orElseThrow(()-> new NoSuchElementException("Author doesn't exists!"));

        return new AuthorResponseDTO(
                authorByEmail.getId(),
                authorByEmail.getFirstName(),
                authorByEmail.getLastName(),
                authorByEmail.getEmail(),
                authorByEmail.getContactNo());
    }

    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> allAuthors = authorRepository.findAll();
        List<AuthorResponseDTO> authorResponseDTOS = new ArrayList<>();
        for (Author author: allAuthors){
            authorResponseDTOS.add(new AuthorResponseDTO(
                    author.getId(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getContactNo()
            ));
        }
        return authorResponseDTOS;
    }

    @Override
    public void updateAuthor(AuthorRequestDTO newAuthorRequestDto, Long id) {

        Author newAuthor = new Author();
        newAuthor.setFirstName(newAuthorRequestDto.getFirstName());
        newAuthor.setLastName(newAuthorRequestDto.getLastName());
        newAuthor.setEmail(newAuthorRequestDto.getEmail());
        newAuthor.setContactNo(newAuthorRequestDto.getContactNo());

        // check author exists for the id
        Author existingAuthor = authorRepository.findAuthorById(id).orElseThrow(() -> new NoSuchElementException("Unable to find author"));

        // if author exists, check the new credentials are valid
        AuthorCredentialsValidator validator = new AuthorCredentialsValidator(newAuthor);
        if (validator.isAuthorValid()) {
            throw new InvalidCredentialFormatException("Invalid credential format detected");
        }

        // check whether the new email already exists in the existing author
        if (!Objects.equals(existingAuthor.getEmail(), newAuthor.getEmail())){
            // if not, check whether the new email exists in the database
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
    @Transactional
    public void deleteAuthor(String email) {
        Author author = authorRepository.findAuthorByEmail(email).orElseThrow(() -> new NoSuchElementException("Unable to find author"));

        List<Book> booksByAuthor = bookRepository.findBooksByAuthor(author);

        booksByAuthor.forEach(book -> {
            bookRepository.deleteUserBooksByBookIsbn(book.getIsbn());
        });

        bookRepository.deleteAll(booksByAuthor);

        authorRepository.delete(author);
    }

    @Override
    public boolean isAuthorExist(String email) {
        return authorRepository.findAuthorByEmail(email).isPresent();
        }
}
