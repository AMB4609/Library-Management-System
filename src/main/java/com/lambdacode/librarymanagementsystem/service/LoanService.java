package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;
import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;
import com.lambdacode.librarymanagementsystem.model.Loan;

import java.util.List;

public interface LoanService {

    LoanDTO loanBook(String userEmail, LoanDTO loanDTO);

    List<LoanDTO> getAllLoans();

    ReturnDTO returnBook(String userEmail, ReturnDTO returnDTO);

    LoanDTO deleteLoan(LoanDTO loanDTO);
}
