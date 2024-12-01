package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;
import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;
import com.lambdacode.librarymanagementsystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_LIBRARIAN_OR_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_USER;
import static com.lambdacode.librarymanagementsystem.constant.LoanConstant.*;

@RestController
@RequestMapping(LOAN)
public class LoanController {
    @Autowired
    private LoanService loanService;
    @PostMapping(ADD_LOAN)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public LoanDTO addLoan(@RequestBody LoanDTO loanDTO) {
        LoanDTO loanz = loanService.loanBook(loanDTO);
        return loanz;
    }
    @GetMapping(GET_ALL_LOANS)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
         return ResponseEntity.ok(loanService.getAllLoans());
    }
    @DeleteMapping(DELETE_LOAN)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<String> deleteLoan(@RequestBody LoanDTO loanDTO) {
            return ResponseEntity.ok("DELETED");

    }
    @PostMapping(RETURN_LOAN)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<ReturnDTO> returnLoan(@RequestBody ReturnDTO returnDTO) {
            ReturnDTO loanz = loanService.returnBook(returnDTO);
            return ResponseEntity.ok(loanz);
    }
    @GetMapping(GET_ALL_LOAN_BY_USER_ID)
    @PreAuthorize(HAS_ROLE_USER)
    public ResponseEntity<Object> getAllLoanByUserId(@RequestBody LoanDTO loanDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok().body(loanService.getAllLoanByUserId(userEmail,loanDTO));
    }
    @GetMapping(GET_LOAN_BY_USER_ID)
    @PreAuthorize(HAS_ROLE_USER)
    public ResponseEntity<Object> getLoanByUserId(@RequestBody LoanDTO loanDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok().body(loanService.getLoanByUserId(userEmail,loanDTO));
    }
}
