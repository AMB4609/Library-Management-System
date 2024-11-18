package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BranchDTO {
    private Long branchId;
    private Long staffId;
    private String branchName;
    private String branchLocation;
    private String contact;
    private String openingTime;
    private String closingTime;
    private Integer numberOfEmployees;
    private Boolean isActive;
}
