package com.lambdacode.librarymanagementsystem.service.branchImpl;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.BranchDTO;
import com.lambdacode.librarymanagementsystem.mapper.BranchMapper;
import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.model.Books;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.BranchRepository;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private BookRepository bookRepository;
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
//        List<Books> books = branchDTO.getBookIds().stream()
//                .map(bookRepository::findById)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
        branch.setBranchLocation(branchDTO.getBranchLocation());
        branch.setNumberOfEmployees(branchDTO.getNumberOfEmployees());
        branch.setOpeningTime(LocalTime.parse(branchDTO.getOpeningTime()));
        branch.setClosingTime(LocalTime.parse(branchDTO.getClosingTime()));
        branch.setContact(branchDTO.getContact());
        branch.setStaff(staff);
//        branch.setBooks(books);
        branchRepository.save(branch);
        return branch;
    }

    @Override
    public Branch deleteBranch(BranchDTO branchDTO){
        Optional<Branch> branchOptional = branchRepository.findById(branchDTO.getBranchId());
        if(branchOptional.isPresent()){
            Branch branch = branchOptional.get();
            branchRepository.delete(branch);
            return branch;
        }else{
            throw new NoSuchElementException("Branch not found for ID: " + branchDTO.getBranchId());
        }

    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branchMapper.toBranchDTOs(branches);
    }


    @Override
    public BranchDTO getBranchById(BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchDTO.getBranchId()));
        if(branch != null){
            return branchMapper.toBranchDTO(branch);
        }else{
            throw new NoSuchElementException("Branch not found for ID: " + branchDTO.getBranchId());
        }
    }
}
