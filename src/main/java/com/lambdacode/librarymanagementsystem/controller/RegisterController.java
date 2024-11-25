package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.RegisterDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/registerUser")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO){
            User savedUser = registerService.registerUser(registerDTO);
            return ResponseEntity.ok(savedUser);
    }
    @PostMapping("/registerStaff")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Staff> registerStaff(@RequestBody RegisterDTO registerDTO){
            Staff savedStaff = registerService.registerStaff(registerDTO);
            return ResponseEntity.ok(savedStaff);
    }
    @GetMapping("/testAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String testAdminAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Access granted for " + authentication.getName();
    }

}
