package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BaseDTO;
import com.lambdacode.librarymanagementsystem.dto.RegisterDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;

public interface RegisterService {
    BaseDTO registerUser(RegisterDTO registerDTO) ;

    BaseDTO registerStaff(RegisterDTO registerDTO);
}
