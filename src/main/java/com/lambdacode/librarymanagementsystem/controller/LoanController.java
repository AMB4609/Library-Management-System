package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;

import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;
import com.lambdacode.librarymanagementsystem.model.Loan;
import com.lambdacode.librarymanagementsystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public LoanDTO getLoan(@RequestBody LoanDTO loanDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Assuming username is the user email
        LoanDTO loanz = loanService.loanBook(userEmail, loanDTO);
        return loanz;
    }
    @GetMapping("/getAllLoans")
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
     try{
         return ResponseEntity.ok(loanService.getAllLoans());
     }catch (Exception e){
         return ResponseEntity.badRequest().build();
     }
    }
    @DeleteMapping("/deleteLoan")
    public ResponseEntity<String> deleteLoan(@RequestBody LoanDTO loanDTO) {
        try{
            loanService.deleteLoan(loanDTO);
            return ResponseEntity.ok("DELETED" + loanDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/returnLoan")
    public ResponseEntity<ReturnDTO> returnLoan(@RequestBody ReturnDTO returnDTO) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();
            ReturnDTO loanz = loanService.returnBook(userEmail, returnDTO);
            return ResponseEntity.ok(loanz);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
