package com.lambdacode.librarymanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JwtResponse extends BaseDTO {
    private Object token;

    public JwtResponse(Object token) {
        this.token = token;
    }
}

