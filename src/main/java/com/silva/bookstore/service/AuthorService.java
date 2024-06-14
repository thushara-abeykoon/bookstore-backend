package com.silva.bookstore.service;

import com.silva.bookstore.dto.AuthorRequestDTO;
import com.silva.bookstore.dto.AuthorResponseDTO;
import com.silva.bookstore.model.Author;

import java.util.List;

public interface AuthorService {
    void addNewAuthor(AuthorRequestDTO author);

    List<AuthorResponseDTO> getAllAuthors();

    AuthorResponseDTO getAuthor(String email);

    void updateAuthor(AuthorRequestDTO author, Long id);

    void deleteAuthor(String email);

    boolean isAuthorExist(String email);
}
