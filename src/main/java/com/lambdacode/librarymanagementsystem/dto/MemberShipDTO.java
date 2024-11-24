package com.lambdacode.librarymanagementsystem.dto;

import com.lambdacode.librarymanagementsystem.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
