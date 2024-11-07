package com.lambdacode.librarymanagementsystem.service.loginImpl;

import com.lambdacode.librarymanagementsystem.dto.LoginDTO;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
@Autowired
private UserRepository userRepository;

    @Override
    public User loginUser(LoginDTO loginDTO) throws Exception {
//            Optional<User> optionalUser = userRepository.findByUserEmail(loginDTO.getUserEmail());
//            if (!optionalUser.isPresent()) {
//                throw new Exception("User does not exist");
//            }
//            User user = optionalUser.get();
//            if (user.getUserPassword().equals(loginDTO.getPassword())) {
//                return user; // Login successful
//            } else {
//                throw new Exception("Wrong password");
//            }
        // optional is used because it has different methods such as isPresent() and get and no need to throw while getting the object
        //get() has built in exception noSuchElementException which throws when it does not contain any value
        //best practice to have get() after isPreset()

        User userTryingToLogin = userRepository.findByUserEmail(loginDTO.getUserEmail()).orElseThrow(() -> new Exception("User not found"));


//        if (userRepository.findByUserEmail(loginDTO.getUserEmail()).isEmpty()) {
//            throw new Exception("User does not exist");
//        }
        if (userTryingToLogin.getUserPassword().equals(loginDTO.getUserPassword())) {
            return userTryingToLogin;
        }
        else throw new Exception("Wrong password");
    }
}
