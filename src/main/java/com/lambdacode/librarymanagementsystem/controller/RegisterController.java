package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.BaseDTO;
import com.lambdacode.librarymanagementsystem.dto.RegisterDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_LIBRARIAN_OR_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.RegisterConstant.*;

@RestController
@RequestMapping(REGISTER)
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping(REGISTER_USER)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<BaseDTO> registerUser(@RequestBody RegisterDTO registerDTO){
            BaseDTO savedUser = registerService.registerUser(registerDTO);
            return ResponseEntity.ok(savedUser);
    }
    @PostMapping(REGISTER_STAFF)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<BaseDTO> registerStaff(@RequestBody RegisterDTO registerDTO){
            BaseDTO savedStaff = registerService.registerStaff(registerDTO);
            return ResponseEntity.ok(savedStaff);
    }
//    @GetMapping("/testAdmin")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String testAdminAccess() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return "Access granted for " + authentication.getName();
//    }

}
