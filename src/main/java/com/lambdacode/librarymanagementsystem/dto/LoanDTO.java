package com.lambdacode.librarymanagementsystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class LoanDTO {
    private Long loanId;
    private Long userId;
    private Long bookId;
    private String bookName;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

}
