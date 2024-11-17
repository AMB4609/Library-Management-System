package com.lambdacode.librarymanagementsystem.mapper;

import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.model.Staff;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BranchMapper {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public BranchDTO toBranchDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchId(branch.getBranchId());
        branchDTO.setBranchName(branch.getBranchName());
        branchDTO.setBranchLocation(branch.getBranchLocation());
        branchDTO.setContact(branch.getContact());
        branchDTO.setOpeningTime(branch.getOpeningTime().format(TIME_FORMATTER));
        branchDTO.setClosingTime(branch.getClosingTime().format(TIME_FORMATTER));
        branchDTO.setNumberOfEmployees(branch.getNumberOfEmployees());
        branchDTO.setIsActive(branch.getIsOpen());
        if (branch.getStaff() != null) {
            branchDTO.setStaffId(branch.getStaff().getId());
        }
        return branchDTO;
    }


    public List<BranchDTO> toBranchDTOs(List<Branch> branches) {
        return branches.stream()
                .map(this::toBranchDTO)
                .collect(Collectors.toList());
    }
}
