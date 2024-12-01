package com.lambdacode.librarymanagementsystem.mapper;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;
import com.lambdacode.librarymanagementsystem.model.Loan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {
    public LoanDTO toLoanDTO(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanId(loan.getLoanId());
        loanDTO.setUserId(loan.getUser().getId());
        loanDTO.setBookId(loan.getBooks().getBookId());
        loanDTO.setBookName(loan.getBooks().getBookName());
        loanDTO.setCheckoutDate(loan.getCheckoutDate());
        LocalDate nowDate = LocalDate.now();
        loanDTO.setCheckoutDate(nowDate);
        loanDTO.setDueDate(nowDate.plusDays(30));
        return loanDTO;
    }
    public List<LoanDTO> toLoanDTOs(List<Loan> loans) {
        return loans.stream()
                .map(this::toLoanDTO)
                .collect(Collectors.toList());
    }
}
