package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
public class ReviewDTO {
    private Long reviewAndRatingId;
    private Long userId;
    private Long bookId;
    private Double rating;
    private String review;
    private LocalDate reviewDate;
}
