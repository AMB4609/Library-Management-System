package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateBookDTO {
    private Long bookId;
    private String bookName;
    private Long authorId;
    private String iSBN;
    private String publisherName;
    private Long CategoryId;
    private String bookDescription;
    private Long booksAvailable;

}
