package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
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

    @ManyToMany
    @JoinTable(name = "branch_id")
    private List<Branch> branch;

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

    public Long booksAvailable;

    private Boolean status;

    private LocalDate releaseDate;

}

//custom exception
//custom annotation
