package com.lambdacode.librarymanagementsystem.exception.handler;

import com.lambdacode.librarymanagementsystem.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoRatingException.class)
    public ResponseEntity<String> handleNoRatingException(NoRatingException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)  // Set the status to 404 Not Found
                .body(ex.getMessage());           // Include a custom message in the response body
    }
    @ExceptionHandler(NoBookForLoanException.class)
    public ResponseEntity<String> handleNoBookFoundException(NoBookForLoanException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(ExceededLoanCountException.class)
    public ResponseEntity<String> handleExceededLoanCountException(ExceededLoanCountException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.LOCKED);
    }
    @ExceptionHandler(RatingOutOfRangeException.class)
    public ResponseEntity<String> handleRatingOutOfRange(RatingOutOfRangeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotYourReviewException.class)
    public ResponseEntity<String> handleNotYourReviewException(NotYourReviewException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleStaffAlreadyExistsException(AlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
