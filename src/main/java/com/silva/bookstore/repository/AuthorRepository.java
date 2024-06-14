package com.silva.bookstore.repository;

import com.silva.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByEmail(String email);
    Optional<Author> findAuthorById(Long id);
    Boolean existsAuthorByEmail(String email);
}
