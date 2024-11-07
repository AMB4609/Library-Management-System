package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.LoginDTO;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.service.LoginService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/loginUser")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {
        try{
            return ResponseEntity.ok(loginService.loginUser(loginDTO));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
