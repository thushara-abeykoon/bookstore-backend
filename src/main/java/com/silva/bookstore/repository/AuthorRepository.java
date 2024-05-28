package com.silva.bookstore.repository;

import com.silva.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByEmail(String email);
}
