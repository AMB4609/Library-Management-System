package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.model.Branch;

import java.util.List;

public interface BranchService {
    Branch addBranch(BranchDTO branchDTO);

    Branch deleteBranch(BranchDTO branchDTO);

    List getAllBranches();

    BranchDTO getBranchById(BranchDTO branchDTO);
}
