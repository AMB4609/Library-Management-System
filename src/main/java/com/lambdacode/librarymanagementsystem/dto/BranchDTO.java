package com.lambdacode.librarymanagementsystem.dto;

import com.lambdacode.librarymanagementsystem.model.Books;
import com.lambdacode.librarymanagementsystem.model.Staff;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

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
