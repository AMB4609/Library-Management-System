package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.DeleteUserDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateUserDetailsDTO;
import com.lambdacode.librarymanagementsystem.dto.UserDTO;
import com.lambdacode.librarymanagementsystem.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<User> updateUser(UpdateUserDetailsDTO updateUserDetailsDTO);

    Object deleteUser(DeleteUserDTO deleteUserDTO);

    void deleteAllUsers();

    ResponseEntity<User> getUserById(UserDTO userDTO);

    ResponseEntity<List<User>> getAllUsers();
}
