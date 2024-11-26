package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class getAllBranchesDTO {
    private Long branchId;
    private String branchName;
    private String branchLocation;
    private boolean getIsOpen;
}
