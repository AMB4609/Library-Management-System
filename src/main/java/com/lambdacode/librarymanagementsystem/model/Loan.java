package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue
    private long loanId;

    @ManyToOne
    @JoinColumn(name = "booksId")
    private Books books;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate checkoutDate;

    private LocalDate dueDate;

    private LocalDate returnDate;
}
