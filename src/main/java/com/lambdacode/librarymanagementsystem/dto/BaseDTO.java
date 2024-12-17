package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BaseDTO {
    private Integer code;
    private String message;
    private boolean status;
    private Object data;
}
