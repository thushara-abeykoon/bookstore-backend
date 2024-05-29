package com.silva.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String isbn;
    private String category;
    private String title;
    private int likeCount;
    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;

}
