package com.lambdacode.librarymanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApplicationException extends RuntimeException {
    private final int errorCode;
    private final String errorMessage;
}
