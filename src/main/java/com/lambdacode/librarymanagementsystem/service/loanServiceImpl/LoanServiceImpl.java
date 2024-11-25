package com.lambdacode.librarymanagementsystem.service.loanServiceImpl;

import com.lambdacode.librarymanagementsystem.dto.LoanDTO;
import com.lambdacode.librarymanagementsystem.dto.ReturnDTO;
import com.lambdacode.librarymanagementsystem.exception.ExceededLoanCountException;
import com.lambdacode.librarymanagementsystem.exception.NoBookForLoanException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.mapper.LoanMapper;
import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.model.Loan;
import com.lambdacode.librarymanagementsystem.model.MemberShip;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.LoanRepository;
import com.lambdacode.librarymanagementsystem.repository.MemberShipRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    @Autowired
    private MemberShipRepository memberShipRepository;

    @Override
    public LoanDTO loanBook(LoanDTO loanDTO) {
        User user = userRepository.findById(loanDTO.getUserId()) .orElseThrow(() -> new NotFoundException("User Not Found"));
        if (user.getLoanCount() > 5){
            throw new ExceededLoanCountException("Have reached loan limit, to get extra book  must return loaned book");
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
        return loanMapper.toLoanDTOs(loanRepository.findAll());
    }

    @Override
    public ReturnDTO returnBook(ReturnDTO returnDTO) {
        Loan loan = loanRepository.findById(Math.toIntExact(returnDTO.getLoanId()))
                .orElseThrow(() -> new NotFoundException("Loan not found for ID: " + returnDTO.getLoanId()));
        Book book = bookRepository.findById(loan.getBooks().getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found for ID: " + loan.getBooks().getBookId()));

        if (!loan.getReturnDate().equals(returnDTO.getReturnDate())) {
            book.setBooksAvailable(book.getBooksAvailable() + 1);
            loan.setReturnDate(LocalDate.now());

            bookRepository.save(book);
            loanRepository.save(loan);

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

    @Override
    public Object getAllLoanByUserId(String userEmail, LoanDTO loanDTO) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new NotFoundException("Please login to view your loans, If not registered please contact nearest Library!");
        }
        if (user.getLoanCount() > 0){
            return loanMapper.toLoanDTOs(loanRepository.findAll());
        }else {
            return "There is no loan to show!";
        }
    }

    @Override
    public Object getLoanByUserId(String userEmail, LoanDTO loanDTO) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new NotFoundException("Please login to view your loans, If not registered please contact nearest Library!");
        }
        if (loanRepository.existsById(Math.toIntExact(loanDTO.getLoanId()))) {
            return loanRepository.findById(Math.toIntExact(loanDTO.getLoanId()));
        }else {
            return "There is no loan to show!";
        }
    }

    public void applyFine(Loan loan) {
        MemberShip membership = memberShipRepository.findByUserId(loan.getUser().getId())
                .orElseThrow(() ->  new NotFoundException("user id not found"));
        if (membership != null) {
            Long fine = 500L;  // Fixed fine rate
            membership.setPayableAmount(membership.getPayableAmount() + fine);
            memberShipRepository.save(membership);
        }
    }
    @Scheduled(cron = "0 0 1 * * ?")  // Runs at 1 AM every day
    @Transactional
    public void applyFinesToOverdueLoans() {
        List<Loan> allLoans = loanRepository.findAll();
        List<Loan> overdueLoans = new ArrayList<>();
        for (Loan loan : allLoans) {
            if (loan.getDueDate().isBefore(LocalDate.now())){
                overdueLoans.add(loan);
            }
        }
        for (Loan loan : overdueLoans) {
           applyFine(loan);
        }
    }
}
