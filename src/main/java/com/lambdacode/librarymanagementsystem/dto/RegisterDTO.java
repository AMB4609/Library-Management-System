package com.lambdacode.librarymanagementsystem.dto;

import com.lambdacode.librarymanagementsystem.model.Branch;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDTO {
    private Long userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Long userPhone;
    private String userAddress;
    private Long StaffId;
    private Branch branch;
    private String staffName;
    private String staffPassword;
    private String staffPhone;
    private String staffEmail;
    private String staffAddress;

    private String position;
}
