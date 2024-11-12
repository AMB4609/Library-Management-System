package com.lambdacode.librarymanagementsystem.service.UserServiceImpl;

import com.lambdacode.librarymanagementsystem.dto.DeleteUserDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateUserDetailsDTO;
import com.lambdacode.librarymanagementsystem.dto.UserDTO;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<User> updateUser(UpdateUserDetailsDTO updateUserDetailsDTO) {

        Optional<User> UserDetails = userRepository.findById(updateUserDetailsDTO.getUserId());
        User user = UserDetails.get();
        if (!UserDetails.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.setAddress(updateUserDetailsDTO.getUserAddress());
        user.setName(updateUserDetailsDTO.getUserName());
        user.setEmail(updateUserDetailsDTO.getUserEmail());
        user.setPhone(updateUserDetailsDTO.getUserPhone());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public Object deleteUser(DeleteUserDTO deleteUserDTO) {
        Optional<User> UserDetails = userRepository.findById(deleteUserDTO.getUserId());
        User user = UserDetails.get();
        if (!UserDetails.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userRepository.delete(user);
        return ResponseEntity.ok();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public ResponseEntity<User> getUserById(UserDTO userDTO) {
        Optional<User> UserDetails = userRepository.findById(userDTO.getUserId());
        User user = UserDetails.get();
        if (UserDetails.isPresent()) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
