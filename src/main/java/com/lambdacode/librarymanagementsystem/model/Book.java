package com.lambdacode.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "book_branch",
//            joinColumns = { @JoinColumn(name = "book_id") },
//            inverseJoinColumns = { @JoinColumn(name = "branch_id") }
//    )
//    private List<Branch> branches = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String publisher;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String bookName;

    private String bookDescription;

    private String iSBN;

    private Long booksAvailable;

    private LocalDate releaseDate;

//    @OneToMany
//    @JoinColumn(name = "reviewAndRatingId")
//    @JsonManagedReference
//    private List<ReviewAndRating> reviews;
    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ReviewAndRating> reviews;
//


    private Double averageRating;

    @Transient
    public boolean getIsAvailable() {
        if (booksAvailable == null || booksAvailable == 0) {
            return false;
        }
        else {
            return true;
        }
    }

}

//custom exception
//custom annotation
