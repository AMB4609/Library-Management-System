package com.lambdacode.librarymanagementsystem.dto;

import com.lambdacode.librarymanagementsystem.model.Branch;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StaffDTO {
    private Long staffId;
    private String staffName;
    private Long branchId;
    private String staffPassword;
    private String staffEmail;
    private Long staffPhone;
    private String staffAddress;
    private String position;

}
