package com.lambdacode.librarymanagementsystem.exception;

public class RatingOutOfRangeException extends RuntimeException {
    public RatingOutOfRangeException(String message) {
        super(message);
    }
}
