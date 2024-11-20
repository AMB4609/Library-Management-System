package com.lambdacode.librarymanagementsystem.dto;

import com.lambdacode.librarymanagementsystem.model.Branch;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDTO {
    private Long id;
    private String name;
    private String password;
    private Long phone;
    private String email;
    private String address;
    private Long branchId;
    private String position;
}
