package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;

import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;
import com.lambdacode.librarymanagementsystem.model.Loan;
import com.lambdacode.librarymanagementsystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;
    @PostMapping("/getLoan")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public LoanDTO getLoan(@RequestBody LoanDTO loanDTO) {
        LoanDTO loanz = loanService.loanBook(loanDTO);
        return loanz;
    }
    @GetMapping("/getAllLoans")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
         return ResponseEntity.ok(loanService.getAllLoans());
    }
    @DeleteMapping("/deleteLoan")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<String> deleteLoan(@RequestBody LoanDTO loanDTO) {
            return ResponseEntity.ok("DELETED");

    }
    @PostMapping("/returnLoan")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<ReturnDTO> returnLoan(@RequestBody ReturnDTO returnDTO) {
            ReturnDTO loanz = loanService.returnBook(returnDTO);
            return ResponseEntity.ok(loanz);
    }
    @GetMapping("/getAllLoanByUserId")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getAllLoanByUserId(@RequestBody LoanDTO loanDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok().body(loanService.getAllLoanByUserId(userEmail,loanDTO));
    }
    @GetMapping("/getLoanByUserId")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getLoanByUserId(@RequestBody LoanDTO loanDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok().body(loanService.getLoanByUserId(userEmail,loanDTO));
    }
}
