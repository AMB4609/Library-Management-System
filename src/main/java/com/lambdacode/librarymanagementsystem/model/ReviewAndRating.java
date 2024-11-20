package com.lambdacode.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lambdacode.librarymanagementsystem.exception.RatingOutOfRangeException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "ReviewAndRating")
public class ReviewAndRating {
    @Id
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewAndRatingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading for performance
    @JoinColumn(name = "books_book_id")
    @JsonBackReference// Foreign key in the ReviewAndRating table
    private Book book;  // Reference to the Book entity

//    @ManyToOne
//    @JsonBackReference
//    private Book books;

    private Double rating;

    private String review;

    private LocalDate reviewDate;

    private Integer likeCount = 0;

    public void addLike() {
        this.likeCount++;
    }

    public void setRating(Double rating) {
        if (rating < 0 || rating > 5) {
            throw new RatingOutOfRangeException("Rating must be between 0 and 5.");
        }
        this.rating = rating;
    }

}
