package com.silva.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
}
