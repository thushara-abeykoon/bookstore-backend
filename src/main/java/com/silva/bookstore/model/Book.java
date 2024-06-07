package com.silva.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import java.util.HashSet;


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
    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;
    @JsonIgnore
    @ManyToMany(mappedBy = "likedBooks")
    private Set<UserEntity> likedUsers = new HashSet<>();
}
