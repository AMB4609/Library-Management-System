package com.lambdacode.librarymanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterDTO {
    private String name;
    private String password;
    private Long phone;
    private String email;
    private String address;
    private Long branchId;
    private String position;
}
