package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.RegisterDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;

public interface RegisterService {
    User registerUser(RegisterDTO registerDTO) throws Exception;

    Staff registerStaff(RegisterDTO registerDTO) throws Exception;
}
