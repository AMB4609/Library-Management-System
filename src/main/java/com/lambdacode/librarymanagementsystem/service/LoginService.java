package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.LoginDTO;

public interface LoginService {
    Object verifyLogin(LoginDTO loginDTO) throws Exception;

}
