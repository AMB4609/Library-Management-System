package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.*;
import com.lambdacode.librarymanagementsystem.model.Branch;

import java.util.List;

public interface BranchService {
    BaseDTO addBranch(BranchDTO branchDTO);

    BaseDTO deleteBranch(Long branchId);

    BaseDTO getAllBranches();

    BaseDTO getBranchById(Long branchId);

    BaseDTO updateBranchById(BranchDTO branchDTO);
}
