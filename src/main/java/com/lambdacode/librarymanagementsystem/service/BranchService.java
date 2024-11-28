package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.dto.BranchIdDTO;
import com.lambdacode.librarymanagementsystem.dto.GetAllBranchesDTO;
import com.lambdacode.librarymanagementsystem.dto.GetBranchByIdDTO;
import com.lambdacode.librarymanagementsystem.model.Branch;

import java.util.List;

public interface BranchService {
    GetBranchByIdDTO addBranch(BranchDTO branchDTO);

    Branch deleteBranch(BranchIdDTO branchIdDTO);

    List<GetAllBranchesDTO> getAllBranches();

    GetBranchByIdDTO getBranchById(BranchIdDTO branchIdDTO);
}
