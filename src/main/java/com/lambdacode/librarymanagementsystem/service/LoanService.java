package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;
import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;

import java.util.List;

public interface LoanService {

    LoanDTO loanBook(LoanDTO loanDTO);

    List<LoanDTO> getAllLoans();

    ReturnDTO returnBook(ReturnDTO returnDTO);

    void deleteLoan(LoanDTO loanDTO);

    Object getAllLoanByUserId(String userEmail, LoanDTO loanDTO);

    Object getLoanByUserId(String userEmail, LoanDTO loanDTO);
}
