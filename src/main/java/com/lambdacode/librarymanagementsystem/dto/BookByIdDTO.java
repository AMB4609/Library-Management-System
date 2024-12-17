package com.lambdacode.librarymanagementsystem.dto;

import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookByIdDTO {
    private Long bookId;
    private Long authorId;
    private String publisher;
    private Long categoryId;
    private String bookName;
    private String bookDescription;
    private String iSBN;
    private Long booksAvailable;
    private LocalDate releaseDate;
    private String authorName;
    private String categoryName;
    private Double averageRating;
    private Boolean isAvailable;
    private List<ReviewAndRating> reviewAndRating;
}
