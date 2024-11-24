package com.lambdacode.librarymanagementsystem.exception;

public class ExceededPaymentException extends RuntimeException {
    public ExceededPaymentException(String message) {
        super(message);
    }
}
