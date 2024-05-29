package com.silva.bookstore.repository;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findBooksByIsbnStartingWith(String isbn);
    List<Book> findBooksByAuthor(Author author);
}
