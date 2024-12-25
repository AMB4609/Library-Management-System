package com.lambdacode.librarymanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetAllBookDTO {
    private Long bookId;
    private String bookName;
private Long booksAvailable;
    private String categoryName;
    private Double averageRating;
    private LocalDate releaseDate;
}
