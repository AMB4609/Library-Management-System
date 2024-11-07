package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.awt.print.Book;
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
    private long reviewAndRatingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    private String rating;

    private String review;

    private LocalDate reviewDate;


}
