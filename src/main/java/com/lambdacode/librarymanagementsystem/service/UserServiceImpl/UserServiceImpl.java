package com.lambdacode.librarymanagementsystem.service.UserServiceImpl;

import com.lambdacode.librarymanagementsystem.dto.*;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private UserRepository userRepository;
    private BaseDTO setResponseFields(Object data, int code, String message, boolean status) {
        BaseDTO dto = new BaseDTO();
        dto.setCode(code);
        dto.setMessage(message);
        dto.setStatus(status);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResponseEntity<User> updateUser(Long id,UpdateUserDetailsDTO updateUserDetailsDTO) {
        try{

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));

            user.setAddress(updateUserDetailsDTO.getUserAddress());
            user.setName(updateUserDetailsDTO.getUserName());
            user.setEmail(updateUserDetailsDTO.getUserEmail());
            user.setPhone(updateUserDetailsDTO.getUserPhone());
            user.setPassword(encoder.encode(updateUserDetailsDTO.getUserPassword()));
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
    public BaseDTO getUserById(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        UserDetailsDTO userDTO = new UserDetailsDTO();
        userDTO.setUserAddress(user.getAddress());
        userDTO.setUserName(user.getName());
        userDTO.setUserEmail(user.getEmail());
        userDTO.setUserPhone(user.getPhone());
        userDTO.setUserId(user.getId());
        return setResponseFields(userDTO,200,"User Retrieved",true);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
