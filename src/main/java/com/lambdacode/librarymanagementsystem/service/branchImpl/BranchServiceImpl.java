package com.lambdacode.librarymanagementsystem.service.branchImpl;

import com.lambdacode.librarymanagementsystem.dto.*;
import com.lambdacode.librarymanagementsystem.exception.ApplicationException;
import com.lambdacode.librarymanagementsystem.mapper.BranchMapper;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.repository.BranchRepository;
import com.lambdacode.librarymanagementsystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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

    private BaseDTO setResponseFields(Object data, int code, String message, boolean status) {
        BaseDTO dto = new BaseDTO();
        dto.setCode(code);
        dto.setMessage(message);
        dto.setStatus(status);
        dto.setData(data);
        return dto;
    }
    // T le j pani banne bhayo one of the generics which makes us not need to cast in every returned value in methods

    @Override
    public BaseDTO addBranch(BranchDTO branchDTO) {
        Branch branch = branchMapper.BranchDTOtoBranch(branchDTO);
        branchRepository.save(branch);
        GetBranchByIdDTO branchByIdDTO = branchMapper.getBranchByIdDTOS(branch);
        return setResponseFields(branchByIdDTO, 200, "Success", true);
    }

    @Override
    public BaseDTO deleteBranch(Long branchId) {
        Branch branch = findBranchById(branchId);
        branchRepository.delete(branch);
        return setResponseFields(branch, 200, "Branches retrieved successfully", true);
    }

    @Override
    public BaseDTO getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        List<GetAllBranchesDTO> dtoList = branchMapper.getAllBranchesDTOS(branches);
        return setResponseFields(dtoList, 200, "Branches retrieved successfully", true);
    }

    @Override
    public BaseDTO getBranchById(Long branchId) {
        Branch branch = findBranchById(branchId);
        GetBranchByIdDTO dto = branchMapper.getBranchByIdDTOS(branch);
        return setResponseFields(dto, 200, "Branch retrieved successfully", true);
    }
    @Override
    public BaseDTO updateBranchById(BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchDTO.getBranchId()).orElseThrow(
                () -> new ApplicationException(404, "Branch not found for ID: " + branchDTO.getBranchId())
        );
        branch.setBranchId(branchDTO.getBranchId());
        branch.setBranchLocation(branchDTO.getBranchLocation());
        branch.setBranchName(branchDTO.getBranchName());
        branch.setContact(branchDTO.getContact());
        branch.setOpeningTime(LocalTime.parse(branchDTO.getOpeningTime()));
        branch.setClosingTime(LocalTime.parse(branchDTO.getClosingTime()));
        branchRepository.save(branch);
        return setResponseFields(branchDTO, 200, "Branch updated successfully", true);
    }
}
