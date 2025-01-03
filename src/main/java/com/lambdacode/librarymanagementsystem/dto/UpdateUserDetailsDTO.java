package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UpdateUserDetailsDTO {

    private String userName;

    private String userEmail;

    private Long userPhone;

    private String userAddress;

    private String userPassword;
}
