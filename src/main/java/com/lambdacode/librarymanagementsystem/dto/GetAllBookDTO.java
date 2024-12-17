package com.lambdacode.librarymanagementsystem.dto;

import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
