package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping("/addBranch")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Branch> addBranch(@RequestBody BranchDTO branchDTO) {

            Branch branch = branchService.addBranch(branchDTO);

            branch.getIsOpen();
            return ResponseEntity.ok(branch);
    }
@DeleteMapping("/deleteBranch")
public ResponseEntity<Branch> deleteBranch(@RequestBody BranchDTO branchDTO) {
            Branch branch = branchService.deleteBranch(branchDTO);
            return ResponseEntity.ok(branch);
}
@GetMapping("/getAllBranches")
        public ResponseEntity<List<BranchDTO>> getAllBranches() {
            return ResponseEntity.ok(branchService.getAllBranches());
    }
    @GetMapping("/getBranchById")
    public ResponseEntity<BranchDTO> getBranchById(@RequestBody BranchDTO branchDTO) {

            BranchDTO getbranch = branchService.getBranchById(branchDTO);
        return ResponseEntity.ok(getbranch);
    }
}

