package com.silva.bookstore.service.impl;

import com.silva.bookstore.dto.AuthorResponseDTO;
import com.silva.bookstore.dto.BookResponseDTO;
import com.silva.bookstore.model.Author;
import com.silva.bookstore.model.Book;
import com.silva.bookstore.model.UserEntity;
import com.silva.bookstore.repository.AuthorRepository;
import com.silva.bookstore.repository.BookRepository;
import com.silva.bookstore.repository.UserRepository;
import com.silva.bookstore.service.AuthorService;
import com.silva.bookstore.service.BookService;
import com.silva.bookstore.service.util.BookCredentialsValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookResponseDTO> getBooks() {
        List<Book> allBooks = bookRepository.findAll();
        return getBookResponseDTOList(allBooks);
    }

    private List<BookResponseDTO> getBookResponseDTOList(List<Book> books) {
        List<BookResponseDTO> bookResponseList = new ArrayList<>();
        books.forEach(book -> {
            bookResponseList.add(convertToBookResponseDTO(book));
        } );
        return bookResponseList;
    }

    private BookResponseDTO convertToBookResponseDTO(Book book){
        return new BookResponseDTO(
                book.getIsbn(),
                book.getCategory(),
                book.getTitle(),
                book.getLikedUsers().size(),
                new AuthorResponseDTO(  book.getAuthor().getId(),
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName(),
                        book.getAuthor().getEmail(),
                        book.getAuthor().getContactNo()));
    }

    @Override
    public void likeBook(Long userId, String bookIsbn) {
        UserEntity user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User doesn't exists"));
        Book book = bookRepository.findByIsbn(bookIsbn).orElseThrow(()-> new NoSuchElementException("Book doesn't exists"));

        user.getLikedBooks().add(book);
        book.getLikedUsers().add(user);

        userRepository.save(user);
        bookRepository.save(book);
    }

    @Override
    public List<BookResponseDTO> searchBooks(String isbn) {
        List<Book> booksStartWithIsbn = bookRepository.findBooksByIsbnStartingWith(isbn);
        return getBookResponseDTOList(booksStartWithIsbn);
    }

    @Override
    public List<BookResponseDTO> searchBooksByAuthor(String email) {
        Author author = authorRepository.findAuthorByEmail(email).orElseThrow(()-> new NoSuchElementException("Author doesn't exists!"));
        List<Book> booksByAuthor = searchBooksByAuthor(author);
        return getBookResponseDTOList(booksByAuthor);
    }

    public List<Book> searchBooksByAuthor(Author author) {
        return bookRepository.findBooksByAuthor(author);
    }

    @Override
    public BookResponseDTO getBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(()->new NoSuchElementException("Book doesn't exists"));
        return convertToBookResponseDTO(book);
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
        if (!authorRepository.existsAuthorByEmail(book.getAuthor().getEmail())  )
            throw new IllegalStateException("Author doesn't exist");

        if(!new BookCredentialsValidator(book).isBookValid())
            throw new IllegalStateException("Book isbn or title doesn't valid");

        bookRepository.save(book);
    }

    // has to update
    @Override
    public void deleteBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(()-> new IllegalStateException("Book doesn't exist"));
        bookRepository.deleteUserBooksByBookIsbn(book.getIsbn());
        bookRepository.delete(book);
    }
}
