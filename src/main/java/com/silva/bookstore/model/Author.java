package com.silva.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table
public class Author {
    @Id
    @GeneratedValue(generator = "sequence_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence_generator", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNo=" + contactNo +
                '}';
    }
}
