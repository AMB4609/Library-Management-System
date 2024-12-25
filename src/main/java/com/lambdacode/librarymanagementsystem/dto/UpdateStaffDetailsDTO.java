package com.lambdacode.librarymanagementsystem.dto;


import lombok.Data;

@Data
public class UpdateStaffDetailsDTO {
    private String staffName;
    private String staffPassword;
    private String staffEmail;
    private Long staffPhone;
    private String staffAddress;
}
