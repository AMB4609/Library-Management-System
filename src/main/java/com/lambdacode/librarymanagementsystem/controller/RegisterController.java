package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.RegisterDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO){
        try {
            User savedUser = registerService.registerUser(registerDTO);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PostMapping("/registerStaff")
    public ResponseEntity<Staff> registerStaff(@RequestBody RegisterDTO registerDTO){
        try{
            Staff savedStaff = registerService.registerStaff(registerDTO);
            return ResponseEntity.ok(savedStaff);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
