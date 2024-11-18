package com.lambdacode.librarymanagementsystem.exception;

public class NoBookForLoanException extends RuntimeException {
    public NoBookForLoanException(String message) {
        super(message);
    }
}
