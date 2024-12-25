package com.lambdacode.librarymanagementsystem.exception.handler;

import com.lambdacode.librarymanagementsystem.dto.BaseDTO;
import com.lambdacode.librarymanagementsystem.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseDTO> exception(Exception e) {
        BaseDTO dto = new BaseDTO();
        dto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dto.setMessage(e.getMessage());
        log.error("",e);
        dto.setStatus(false);
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);

        //log chai padhna saknu paryo
        //optimization of code
        // dont dto.setMessage(e.getMessage());
        //log level
        //test case
        //
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<BaseDTO> applicationException(ApplicationException e) {
        BaseDTO dto = new BaseDTO();
        dto.setCode(e.getErrorCode());
        dto.setMessage(e.getErrorMessage());
        dto.setStatus(false);
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }


//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<BaseDTO> handleNoSuchElementException(NoSuchElementException ex) {
//        BaseDTO dto = new BaseDTO();
//        dto.setCode(HttpStatus.NOT_FOUND.value());
//                dto.setMessage(ex.getMessage());
//
//        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(NoRatingException.class)
//    public ResponseEntity<String> handleNoRatingException(NoRatingException ex) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)  // Set the status to 404 Not Found
//                .body(ex.getMessage());           // Include a custom message in the response body
//    }
//    @ExceptionHandler(NoBookForLoanException.class)
//    public ResponseEntity<String> handleNoBookFoundException(NoBookForLoanException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//
//    }
//    @ExceptionHandler(ExceededLoanCountException.class)
//    public ResponseEntity<String> handleExceededLoanCountException(ExceededLoanCountException ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.LOCKED);
//    }
//    @ExceptionHandler(RatingOutOfRangeException.class)
//    public ResponseEntity<String> handleRatingOutOfRange(RatingOutOfRangeException ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(NotYourReviewException.class)
//    public ResponseEntity<String> handleNotYourReviewException(NotYourReviewException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(AlreadyExistsException.class)
//    public ResponseEntity<String> handleStaffAlreadyExistsException(AlreadyExistsException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(ExceededPaymentException.class)
//    public ResponseEntity<String> handleExceededPaymentException(ExceededPaymentException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(PayFirstException.class)
//    public ResponseEntity<String> handlePayFirstException(PayFirstException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}
