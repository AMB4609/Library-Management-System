package com.lambdacode.librarymanagementsystem.service.loanServiceImpl;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;
import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;
import com.lambdacode.librarymanagementsystem.exception.ExceededLoanCountException;
import com.lambdacode.librarymanagementsystem.exception.NoBookForLoanException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.mapper.LoanMapper;
import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.model.Loan;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.LoanRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
            throw new NotFoundException("User not found with email: " + userEmail);
        }
        if (user.getLoanCount() > 5){
            throw new ExceededLoanCountException("You have reached your loan limit, to get extra book you must return the book you have taken");
        }
        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found with ID: " + loanDTO.getBookId()));

        if (!book.getIsAvailable()) {
            throw new NoBookForLoanException("Book is not available for loan");
        }

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBooks(book);
        loan.setCheckoutDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(30));
        loanRepository.save(loan);
        user.setLoanCount(user.getLoanCount() + 1);
        userRepository.save(user);
        book.setBooksAvailable(book.getBooksAvailable() - 1);
        bookRepository.save(book);
        return loanMapper.toLoanDTO(loan);
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        return loanMapper.toBranchDTOs(loanRepository.findAll());
    }

    @Override
    public ReturnDTO returnBook(String userEmail, ReturnDTO returnDTO) {
        Loan loan = loanRepository.findById(Math.toIntExact(returnDTO.getLoanId()))
                .orElseThrow(() -> new NoSuchElementException("Loan not found for ID: " + returnDTO.getLoanId()));
        Book book = bookRepository.findById(loan.getBooks().getBookId())
                .orElseThrow(() -> new NoSuchElementException("Book not found for ID: " + loan.getBooks().getBookId()));

        if (!loan.getReturnDate().equals(returnDTO.getReturnDate())) {
            book.setBooksAvailable(book.getBooksAvailable() + 1);
            loan.setReturnDate(LocalDate.now());

            bookRepository.save(book);
            loanRepository.save(loan);
            loanRepository.delete(loan);

            returnDTO.setReturnDate(LocalDate.now());
            returnDTO.setBookName(book.getBookName());
            returnDTO.setLoanId(loan.getLoanId());
            returnDTO.setCheckoutDate(loan.getCheckoutDate());
            returnDTO.setDueDate(loan.getDueDate());
            return returnDTO;
        } else {
            throw new IllegalStateException("Loan you are trying to return is not there or already returned");
        }
    }

    @Override
    public LoanDTO deleteLoan(LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(Math.toIntExact(loanDTO.getLoanId()))
                .orElseThrow(() -> new NoSuchElementException("Loan not found for ID: " + loanDTO.getLoanId()));
        loanRepository.delete(loan);
        return loanDTO;
    }
}
