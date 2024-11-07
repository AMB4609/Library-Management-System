package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "FeeAndPayment")
public class FeeAndPayment {
    @Id
    @GeneratedValue
    private Long feeAndPaymentId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String type;

    private Long amount;

    private LocalDate dateIssued;

    private LocalDate dueDate;

    private String status;

}
