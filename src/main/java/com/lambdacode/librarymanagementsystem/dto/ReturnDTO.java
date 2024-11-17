package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ReturnDTO {
    private Long loanId;
    private String bookName;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}
