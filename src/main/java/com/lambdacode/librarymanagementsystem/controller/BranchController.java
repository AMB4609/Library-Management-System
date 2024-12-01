package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.*;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.BranchConstant.*;

@RestController
@RequestMapping(BRANCH)
@Validated
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping(ADD_BRANCH)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<GetBranchByIdDTO> addBranch(@Valid @RequestBody BranchDTO branchDTO) {

            GetBranchByIdDTO branchDTO2 = branchService.addBranch(branchDTO);
            return ResponseEntity.ok(branchDTO2);
    }
@DeleteMapping(DELETE_BRANCH)
@PreAuthorize(HAS_ROLE_ADMIN)
public ResponseEntity<Branch> deleteBranch(@RequestBody BranchIdDTO branchIdDTO) {
            Branch branch = branchService.deleteBranch(branchIdDTO);
            return ResponseEntity.ok(branch);
}
    @GetMapping(GET_ALL_BRANCHES)
    public ResponseEntity<List<GetAllBranchesDTO>> getAllBranches() {
        List<GetAllBranchesDTO> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }

    @GetMapping(GET_BRANCH_BY_ID)
    public ResponseEntity<BaseDTO> getBranchById(@RequestBody BranchIdDTO branchIdDTO) {
        BaseDTO branch = branchService.getBranchById(branchIdDTO);
        return ResponseEntity.ok(branch);
    }
}
