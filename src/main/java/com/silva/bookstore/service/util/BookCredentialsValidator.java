package com.silva.bookstore.service.util;

import com.silva.bookstore.model.Book;

import java.util.regex.Pattern;

public class BookCredentialsValidator extends Validator {

    private final Pattern alphaNumeric = Pattern.compile("^[a-zA-Z0-9]+$");
    private final Pattern alphaNumericWithSpace = Pattern.compile("^[a-zA-Z0-9 ]+$");

    private final Book book;
    public BookCredentialsValidator(Book book) {
        this.book = book;
    }

    public boolean isBookValid() {
        return validate(alphaNumeric, book.getIsbn()) && validate(alphaNumericWithSpace, book.getTitle());
    }


}
