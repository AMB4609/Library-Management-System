package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "Books")
public class Books {
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

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String bookName;

    private String bookPhoto;

    private String iSBN;

    private Long booksAvailable;


    private LocalDate releaseDate;

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
