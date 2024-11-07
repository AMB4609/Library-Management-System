package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.LoginDTO;
import com.lambdacode.librarymanagementsystem.model.User;

public interface LoginService {
    Object loginUser(LoginDTO loginDTO) throws Exception;

}
