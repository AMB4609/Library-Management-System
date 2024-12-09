package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.JwtResponse;
import com.lambdacode.librarymanagementsystem.dto.LoginDTO;
import com.lambdacode.librarymanagementsystem.service.JWTService;
import com.lambdacode.librarymanagementsystem.service.LoginService;
import com.lambdacode.librarymanagementsystem.service.MyUserDetailsServiceImpl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lambdacode.librarymanagementsystem.constant.LoginConstant.LOGIN;
import static com.lambdacode.librarymanagementsystem.constant.LoginConstant.LOGIN_USER;

@RestController
@RequestMapping(LOGIN)
public class LoginController {
    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;
    @Autowired
    private JWTService jwtService;
//    public ResponseEntity<Object> login(@RequestBody String email) {
//        try{
//            return ResponseEntity.ok(userDetailsService.loadUserByUsername(email));
//        }catch(Exception e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
    @Autowired
    private LoginService loginService;
    @PostMapping(LOGIN_USER)
    public ResponseEntity<Object> verifyLogin(@RequestBody LoginDTO loginDTO) throws Exception {
        Object token = loginService.verifyLogin(loginDTO);
        return ResponseEntity.ok(token);
    }
}
