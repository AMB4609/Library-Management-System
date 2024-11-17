package com.lambdacode.librarymanagementsystem.service.loanServiceImpl;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;
import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;
import com.lambdacode.librarymanagementsystem.mapper.LoanMapper;
import com.lambdacode.librarymanagementsystem.model.Books;
import com.lambdacode.librarymanagementsystem.model.Loan;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.BranchRepository;
import com.lambdacode.librarymanagementsystem.repository.LoanRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.LoanService;
import com.lambdacode.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LoanMapper loanMapper;
    @Override
    public LoanDTO loanBook(String userEmail, LoanDTO loanDTO) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + userEmail);
        }
        Books books = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (books.getIsAvailable() == false) {
            throw new RuntimeException("Book is not available for loan");
        }
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBooks(books);
        LocalDate nowDate = LocalDate.now();
        loan.setCheckoutDate(nowDate);
        loan.setDueDate(nowDate.plusDays(30));
        loanRepository.save(loan);
        LoanDTO loanDTO1 =  loanMapper.toLoanDTO(loan);
        books.setBooksAvailable(books.getBooksAvailable() - 1);
        bookRepository.save(books);
        return loanDTO1;
    }
    @Override
    public List<LoanDTO> getAllLoans(){
        return loanMapper.toBranchDTOs(loanRepository.findAll());
    }

    @Override
    public ReturnDTO returnBook(String userEmail, ReturnDTO returnDTO) {
        Integer loanId = Math.toIntExact(returnDTO.getLoanId());
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found for ID: " + returnDTO.getLoanId()));

        if (returnDTO.getLoanId().equals(loan.getLoanId())) {
            Books books = bookRepository.findById(loan.getBooks().getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found for ID: " + loan.getBooks().getBookId()));

            books.setBooksAvailable(books.getBooksAvailable() + 1);
            loan.setReturnDate(LocalDate.now()); // Assuming you want to update the return date

            // Assuming you save these changes
            bookRepository.save(books);
            loanRepository.save(loan);
            returnDTO.setReturnDate(LocalDate.now());
            returnDTO.setBookName(books.getBookName());
            returnDTO.setLoanId(loan.getLoanId());
            returnDTO.setCheckoutDate(loan.getCheckoutDate());
            returnDTO.setDueDate(loan.getDueDate());
            loanRepository.delete(loan);
            return returnDTO;
        } else {
            throw new RuntimeException("Loan you are trying to find is not there or already returned");
        }

    }

    @Override
    public LoanDTO deleteLoan(LoanDTO loanDTO) {
        Integer loanId = Math.toIntExact(loanDTO.getLoanId());
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found for ID: " + loanDTO.getLoanId()));
        loanRepository.delete(loan);
        return loanDTO;
    }
}
