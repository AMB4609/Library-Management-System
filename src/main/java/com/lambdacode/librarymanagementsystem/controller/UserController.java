package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.DeleteUserDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateUserDetailsDTO;
import com.lambdacode.librarymanagementsystem.dto.UserDTO;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PutMapping("/updateUser")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDetailsDTO UpdateUserDetailsDTO) {
        try{
//            User user = userService.updateUser(UpdateUserDetailsDTO);
            return userService.updateUser(UpdateUserDetailsDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
@DeleteMapping("/deleteUser")
@PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@RequestBody DeleteUserDTO deleteUserDTO) {
        try {
            userService.deleteUser(deleteUserDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
    @DeleteMapping("/deleteAllUsers")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAllUsers() {
        try{
            userService.deleteAllUsers();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping("/getUserById")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<User> getUserById(@RequestBody UserDTO userDTO) {
        try{

            return userService.getUserById(userDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        try{
            return userService.getAllUsers();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
