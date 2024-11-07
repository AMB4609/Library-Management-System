package com.lambdacode.librarymanagementsystem.service.loginImpl;

import com.lambdacode.librarymanagementsystem.dto.LoginDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
@Autowired
private StaffRepository staffRepository;
    @Override
    public Object loginUser(LoginDTO loginDTO) throws Exception {
        Optional<User> optionalUser = userRepository.findByUserEmail(loginDTO.getEmail());
        Optional<Staff> optionalStaff = staffRepository.findByStaffEmail(loginDTO.getEmail());

        if (!optionalUser.isPresent() && !optionalStaff.isPresent()) {
            throw new IllegalArgumentException("No such user or staff exists");
        }

        // Check for user login
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getUserPassword().equals(loginDTO.getPassword())) {
                return user;  // Login successful for user
            }
        }

        // Check for staff login
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            if (staff.getStaffPassword().equals(loginDTO.getPassword())) {
                return staff;  // Login successful for staff
            }
        }

        // Fallback, though logically this should never be reached
        throw new RuntimeException("Wrong email or password");
    }
}
        // optional is used because it has different methods such as isPresent() and get and no need to throw while getting the object
        //get() has built in exception noSuchElementException which throws when it does not contain any value
        //best practice to have get() after isPreset()

//        User TryingToLogin = userRepository.findByUserEmail(loginDTO.getUserEmail()).orElseThrow(() -> new Exception("User not found"));


//        if (userRepository.findByUserEmail(loginDTO.getUserEmail()).isEmpty()) {
//            throw new Exception("User does not exist");
//        }
//        if (TryingToLogin.getUserPassword().equals(loginDTO.getUserPassword())) {
//            return TryingToLogin;
//        }
//        else throw new Exception("Wrong password");
//    }
//}
