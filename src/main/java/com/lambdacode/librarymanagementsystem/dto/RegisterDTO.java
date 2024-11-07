package com.lambdacode.librarymanagementsystem.dto;

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
}
