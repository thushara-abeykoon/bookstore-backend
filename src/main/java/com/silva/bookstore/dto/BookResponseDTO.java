package com.silva.bookstore.dto;

import com.silva.bookstore.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponseDTO {
    private String isbn;
    private String category;
    private String title;
    private Integer likeCount;
    private Author author;
}
