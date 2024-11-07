package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateBookDTO {
    private Long bookId;

    private Long booksAvailable;

}
