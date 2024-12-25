package com.lambdacode.librarymanagementsystem.dto;


import lombok.Data;

@Data
public class UserDetailsDTO {
    private Long userId;

    private String userName;

    private String userEmail;

    private Long userPhone;

    private String userAddress;
}
