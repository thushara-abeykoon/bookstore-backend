package com.silva.bookstore.repository;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findBooksByIsbnStartingWith(String isbn);
    List<Book> findBooksByAuthor(Author author);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_book WHERE book_isbn = :isbn", nativeQuery = true)
    void deleteUserBooksByBookIsbn(String isbn);
}
