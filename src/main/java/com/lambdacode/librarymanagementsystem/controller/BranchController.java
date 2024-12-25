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
import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_LIBRARIAN_OR_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.BranchConstant.*;

@RestController
@RequestMapping(BRANCH)
@Validated
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping(ADD_BRANCH)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<BaseDTO> addBranch(@Valid @RequestBody BranchDTO branchDTO) {

        BaseDTO branchDTO2 = branchService.addBranch(branchDTO);
            return ResponseEntity.ok(branchDTO2);
    }
@DeleteMapping(DELETE_BRANCH)
@PreAuthorize(HAS_ROLE_ADMIN)
public ResponseEntity<BaseDTO> deleteBranch(@PathVariable Long branchId) {
    BaseDTO branch = branchService.deleteBranch(branchId);
            return ResponseEntity.ok(branch);
}
    @GetMapping(GET_ALL_BRANCHES)
    public ResponseEntity<BaseDTO> getAllBranches() {
        BaseDTO branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }

    @GetMapping(GET_BRANCH_BY_ID)
    public ResponseEntity<BaseDTO> getBranchById(@PathVariable Long branchId) {
        BaseDTO branch = branchService.getBranchById(branchId);
        return ResponseEntity.ok(branch);
    }
    @PutMapping(UPDATE_BRANCH_DETAILS)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<BaseDTO> updateBranchDetails(@RequestBody BranchDTO branchDTO) {
        BaseDTO branch = branchService.updateBranchById(branchDTO);
        return ResponseEntity.ok(branch);
    }
}
