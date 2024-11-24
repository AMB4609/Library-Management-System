package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "MemberShip")
@Getter
@Setter
public class MemberShip {
    @Id
    @GeneratedValue
    private Integer memberShipId;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    private Long monthsOfMembership;

    private Long payedAmount;

    private Long payableAmount;

    private Long costPerMonth;

    private LocalDate memberShipDate;

    private LocalDate memberShipExpiry;
}
