package com.lambdacode.librarymanagementsystem.exception;

public class ExceededLoanCountException extends RuntimeException {
    public ExceededLoanCountException(String message) {
        super(message);
    }
}
