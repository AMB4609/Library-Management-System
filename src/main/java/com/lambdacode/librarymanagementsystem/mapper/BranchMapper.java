package com.lambdacode.librarymanagementsystem.mapper;

import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.dto.getAllBranchesDTO;
import com.lambdacode.librarymanagementsystem.dto.getBranchByIdDTO;
import com.lambdacode.librarymanagementsystem.model.Branch;
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
        return branchDTO;
    }


    public List<getAllBranchesDTO> getAllBranchesDTOS(List<Branch> branches) {
        return branches.stream()
                .map(this::allBranchesDTO)
                .collect(Collectors.toList());
    }

    private getAllBranchesDTO allBranchesDTO(Branch branch) {
        getAllBranchesDTO allBranchesDTO = new getAllBranchesDTO();
        allBranchesDTO.setBranchId(branch.getBranchId());
        allBranchesDTO.setBranchName(branch.getBranchName());
        allBranchesDTO.setBranchLocation(branch.getBranchLocation());
        allBranchesDTO.setGetIsOpen(branch.getIsOpen());
        return allBranchesDTO;
    }
    
    public getBranchByIdDTO getBranchByIdDTOS(Branch branch){
        getBranchByIdDTO b = new getBranchByIdDTO();
        b.setBranchName(branch.getBranchName());
        b.setBranchLocation(branch.getBranchLocation());
        b.setGetIsOpen(branch.getIsOpen());
        b.setOpeningTime(branch.getOpeningTime());
        b.setClosingTime(branch.getClosingTime());
        b.setGetIsOpen(branch.getIsOpen());
        return b;
    }
}
