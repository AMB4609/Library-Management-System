package com.lambdacode.librarymanagementsystem.service.UserServiceImpl;

import com.lambdacode.librarymanagementsystem.dto.DeleteUserDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateUserDetailsDTO;
import com.lambdacode.librarymanagementsystem.dto.UserDTO;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> updateUser(UpdateUserDetailsDTO updateUserDetailsDTO) {
        try{

            User user = userRepository.findById(updateUserDetailsDTO.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + updateUserDetailsDTO.getUserId()));

            user.setAddress(updateUserDetailsDTO.getUserAddress());
            user.setName(updateUserDetailsDTO.getUserName());
            user.setEmail(updateUserDetailsDTO.getUserEmail());
            user.setPhone(updateUserDetailsDTO.getUserPhone());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }catch (NoSuchElementException e){
            throw e;
        }

    }

    @Override
    public ResponseEntity<?> deleteUser(DeleteUserDTO deleteUserDTO) {
        User user = userRepository.findById(deleteUserDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + deleteUserDTO.getUserId()));

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public ResponseEntity<User> getUserById(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userDTO.getUserId()));

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
