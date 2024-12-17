package com.lambdacode.librarymanagementsystem.mapper;

import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.dto.GetAllBranchesDTO;
import com.lambdacode.librarymanagementsystem.dto.GetBranchByIdDTO;
import com.lambdacode.librarymanagementsystem.model.Branch;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class BranchMapper {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public List<GetAllBranchesDTO> getAllBranchesDTOS(List<Branch> branches) {
        return branches.stream()
                .map(this::allBranchesDTO)
                .collect(Collectors.toList());
    }

    private GetAllBranchesDTO allBranchesDTO(Branch branch) {
        GetAllBranchesDTO allBranchesDTO = new GetAllBranchesDTO();
        allBranchesDTO.setBranchId(branch.getBranchId());
        allBranchesDTO.setBranchName(branch.getBranchName());
        allBranchesDTO.setBranchLocation(branch.getBranchLocation());
        allBranchesDTO.setGetIsOpen(branch.getIsOpen());
        return allBranchesDTO;
    }
    
    public GetBranchByIdDTO getBranchByIdDTOS(Branch branch){
        GetBranchByIdDTO b = new GetBranchByIdDTO();
        b.setBranchName(branch.getBranchName());
        b.setBranchLocation(branch.getBranchLocation());
        b.setGetIsOpen(branch.getIsOpen());
        b.setOpeningTime(branch.getOpeningTime());
        b.setClosingTime(branch.getClosingTime());
        return b;
    }

    public Branch BranchDTOtoBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setBranchName(branchDTO.getBranchName());
        branch.setBranchLocation(branchDTO.getBranchLocation());
        branch.setNumberOfEmployees(branchDTO.getNumberOfEmployees());
        branch.setOpeningTime(LocalTime.parse(branchDTO.getOpeningTime()));
        branch.setClosingTime(LocalTime.parse(branchDTO.getClosingTime()));
        branch.setContact(branchDTO.getContact());
        return branch;
    }
}
