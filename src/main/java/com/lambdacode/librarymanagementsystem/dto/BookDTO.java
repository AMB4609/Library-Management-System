package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class BookDTO {
    private Long bookId;
    private Long authorId;
    private List<Long> branchIds;
    private String publisherName;
    private Long categoryId;
    private String bookName;
    private String ISBN;
    private Long booksAvailable;
    private Boolean status;
    private LocalDate releaseDate;
}
