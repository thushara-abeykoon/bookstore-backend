package com.silva.bookstore.service.util;

import com.silva.bookstore.model.Author;
import com.silva.bookstore.model.Book;
import com.silva.bookstore.repository.AuthorRepository;
import com.silva.bookstore.repository.BookRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class AuthorLikeCountService {
//    private static final Logger logger = Logger.getLogger(AuthorLikeCountService.class.getName());
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//
//    public AuthorLikeCountService(AuthorRepository authorRepository, BookRepository bookRepository) {
//        this.authorRepository = authorRepository;
//        this.bookRepository = bookRepository;
//    }
//
//
//    @Scheduled(fixedRate = 300000)
//    public void reportLikeCount() {
//        List<Author> allAuthors = authorRepository.findAll();
//        Stream<String> logStream = allAuthors.stream().map(author -> {
//            List<Book> booksByAuthor = bookRepository.findBooksByAuthor(author);
//            List<String> bookStreamList = booksByAuthor.stream().map(book -> String.format("ISBN-%s: %d Likes, ", book.getIsbn(), book.getLikeCount())).toList();
//            StringBuilder bookListBuilder = new StringBuilder();
//            for(String book : bookStreamList) {
//                bookListBuilder.append(book);
//            }
//
//            return String.format("%s : {%s}", author.getEmail(), bookListBuilder);
//        });
//
//        logStream.toList().forEach(logger::info);
//    }
}
