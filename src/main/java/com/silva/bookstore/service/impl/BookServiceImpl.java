package com.silva.bookstore.service.impl;

import com.silva.bookstore.model.Book;
import com.silva.bookstore.repository.BookRepository;
import com.silva.bookstore.service.AuthorService;
import com.silva.bookstore.service.BookService;
import com.silva.bookstore.service.util.BookCredentialsValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public void likeBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(()->new IllegalStateException("Book doesn't exist"));
        book.setLikeCount(book.getLikeCount() + 1);
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String isbn) {
        return bookRepository.findBooksByIsbnStartingWith(isbn);
    }

    @Override
    public Book getBook(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        return book.orElse(null);
    }

    @Override
    public void addBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(book.getIsbn());;
        if (bookOptional.isPresent())
            throw new IllegalStateException("Book isbn already exists");

        saveBook(book);
    }

    @Override
    public void updateBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(book.getIsbn());
        if (bookOptional.isEmpty())
            throw new IllegalStateException("Book doesn't exist");

        saveBook(book);
    }

    private void saveBook(Book book){
        if (!authorService.isAuthorExist(book.getAuthor().getEmail()))
            throw new IllegalStateException("Author doesn't exist");

        if(!new BookCredentialsValidator(book).isBookValid())
            throw new IllegalStateException("Book isbn or title doesn't valid");

        bookRepository.save(book);
    }


    @Override
    public void deleteBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(()-> new IllegalStateException("Book doesn't exist"));
        bookRepository.delete(book);
    }
}
