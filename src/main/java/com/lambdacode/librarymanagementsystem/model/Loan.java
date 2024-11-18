package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Book books;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "checkout_date")
    private LocalDate checkoutDate;
@Column(name = "due_date")
    private LocalDate dueDate;

    private LocalDate returnDate;
}
