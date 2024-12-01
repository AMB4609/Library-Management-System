package com.lambdacode.librarymanagementsystem.service.branchImpl;

import com.lambdacode.librarymanagementsystem.dto.*;
import com.lambdacode.librarymanagementsystem.exception.ApplicationException;
import com.lambdacode.librarymanagementsystem.mapper.BranchMapper;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.repository.BranchRepository;
import com.lambdacode.librarymanagementsystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchMapper branchMapper;

    private Branch findBranchById(Long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new ApplicationException(404, "Branch not found for ID: " + branchId));
    }

    private <T extends BaseDTO> T setResponseFields(T dto, int code, String message, boolean status) {
        dto.setCode(code);
        dto.setMessage(message);
        dto.setStatus(status);
        return dto;
    }
    // T le j pani banne bhayo one of the generics which makes us not need to cast in every returned value in methods

    @Override
    public GetBranchByIdDTO addBranch(BranchDTO branchDTO) {
        Branch branch = branchMapper.BranchDTOtoBranch(branchDTO);
        branchRepository.save(branch);
        GetBranchByIdDTO branchByIdDTO = branchMapper.getBranchByIdDTOS(branch);
        return setResponseFields(branchByIdDTO, 200, "Success", true);
    }

    @Override
    public Branch deleteBranch(BranchIdDTO branchIdDTO) {
        Branch branch = findBranchById(branchIdDTO.getBranchId());
        branchRepository.delete(branch);
        return branch;
    }

    @Override
    public List<GetAllBranchesDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        List<GetAllBranchesDTO> dtoList = branchMapper.getAllBranchesDTOS(branches);
        dtoList.forEach(dto -> setResponseFields(dto, 200, "Branches retrieved successfully", true));
        return dtoList;
    }

    @Override
    public GetBranchByIdDTO getBranchById(BranchIdDTO branchIdDTO) {
        Branch branch = findBranchById(branchIdDTO.getBranchId());
        GetBranchByIdDTO dto = branchMapper.getBranchByIdDTOS(branch);
        return setResponseFields(dto, 200, "Branch retrieved successfully", true);
    }
}
