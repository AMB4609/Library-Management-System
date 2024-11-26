package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Data
@Getter
@Setter
public class getBranchByIdDTO {
    private Long branchId;
    private String branchName;
    private String branchLocation;
    private String contact;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private boolean getIsOpen;
}
