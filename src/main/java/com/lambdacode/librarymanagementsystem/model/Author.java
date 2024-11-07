package com.lambdacode.librarymanagementsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "Author")
public class Author {
    @Id
    @GeneratedValue
    private Long authorId;

    private String authorName;

    private String authorSurname;

    private String authorEmail;

    private String biography;
}
