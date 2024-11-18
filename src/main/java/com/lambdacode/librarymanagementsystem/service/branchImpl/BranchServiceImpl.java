package com.lambdacode.librarymanagementsystem.service.branchImpl;

import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.mapper.BranchMapper;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.repository.BranchRepository;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BranchMapper branchMapper;

    @Override
    public Branch addBranch(BranchDTO branchDTO) {
        Staff staff = staffRepository.findById(branchDTO.getStaffId())
                .orElseThrow(() -> new NoSuchElementException("Staff not found for ID: " + branchDTO.getStaffId()));
        Branch branch = new Branch();
        branch.setBranchName(branchDTO.getBranchName());
        branch.setBranchLocation(branchDTO.getBranchLocation());
        branch.setNumberOfEmployees(branchDTO.getNumberOfEmployees());
        branch.setOpeningTime(LocalTime.parse(branchDTO.getOpeningTime()));
        branch.setClosingTime(LocalTime.parse(branchDTO.getClosingTime()));
        branch.setContact(branchDTO.getContact());
        branch.setStaff(staff);
        branchRepository.save(branch);
        return branch;
    }

    @Override
    public Branch deleteBranch(BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchDTO.getBranchId())
                .orElseThrow(() -> new NoSuchElementException("Branch not found for ID: " + branchDTO.getBranchId()));
        branchRepository.delete(branch);
        return branch;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branchMapper.toBranchDTOs(branches);
    }

    @Override
    public BranchDTO getBranchById(BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchDTO.getBranchId())
                .orElseThrow(() -> new NoSuchElementException("Branch not found for ID: " + branchDTO.getBranchId()));
        return branchMapper.toBranchDTO(branch);
    }
}
