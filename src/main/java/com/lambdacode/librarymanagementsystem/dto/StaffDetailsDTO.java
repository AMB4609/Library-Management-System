package com.lambdacode.librarymanagementsystem.dto;


import lombok.Data;

@Data
public class StaffDetailsDTO {
    private Long staffId;
    private String staffName;
    private Long branchId;
    private String staffEmail;
    private Long staffPhone;
    private String staffAddress;
    private String position;
}
