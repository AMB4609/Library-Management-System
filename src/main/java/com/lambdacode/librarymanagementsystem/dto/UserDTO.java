package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserDTO {
    private Long userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private Long userPhone;

    private String userAddress;
}
