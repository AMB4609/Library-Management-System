package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class MemberShipDTO{

    private Integer memberShipId;

    private String userEmail;

    private Long monthsOfMembership;

    private Long payedAmount;

    private Long payableAmount;

    private Long costPerMonth;

    private LocalDate memberShipDate;

    private LocalDate memberShipExpiry;

}
