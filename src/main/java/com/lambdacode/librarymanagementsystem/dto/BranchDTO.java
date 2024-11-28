package com.lambdacode.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BranchDTO {

    @NotBlank(message = "Branch name is mandatory")
    @Size(max = 10, message = "Branch name must not exceed 10 characters")
    private String branchName;

    @NotBlank(message = "location should not be blank")
    @Size(max = 10, message = "Branch name must not exceed 10 characters")
    private String branchLocation;

    @NotBlank(message = "Contact should not be blank")
    @Pattern(regexp = "\\+?[0-9]*", message = "Contact must be a valid phone number")
    private String contact;

    @NotBlank(message = "opening time should not be blank")
    private String openingTime;

    @NotBlank(message = "closing time should not be blank")
    private String closingTime;

    @NotNull(message = "number of employees should not be null")
    private Integer numberOfEmployees;
}
