package com.lambdacode.librarymanagementsystem.service.loginImpl;

import com.lambdacode.librarymanagementsystem.dto.BaseDTO;
import com.lambdacode.librarymanagementsystem.dto.JwtResponse;
import com.lambdacode.librarymanagementsystem.dto.LoginDTO;
import com.lambdacode.librarymanagementsystem.model.StaffPrinciple;
import com.lambdacode.librarymanagementsystem.model.UserPrinciple;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.JWTService;
import com.lambdacode.librarymanagementsystem.service.LoginService;
import com.lambdacode.librarymanagementsystem.service.MyUserDetailsServiceImpl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private String token;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    @Override
    public Object verifyLogin(LoginDTO loginDTO) throws Exception {
        String entityType = null; // Initialize entityType to null
        String role = null;       // Initialize role to null

        // Load UserDetails by email (username)
        UserDetails userDetails = myUserDetailsServiceImpl.loadUserByUsername(loginDTO.getEmail());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        // Check if authentication was successful
        if (authentication.isAuthenticated()) {
            // Determine if the user is a `staff` or a `user`
            if (userDetails instanceof StaffPrinciple) {
                entityType = "staff";
                role = ((StaffPrinciple) userDetails).getPosition();  // Get role/position of the staff member
            } else if (userDetails instanceof UserPrinciple) {
                entityType = "user";
                // role remains null for users since it's not applicable here
            } else {
                // If neither, return unauthorized response
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            // Debug output to verify values of entityType and role
            System.out.println("Entity Type: " + entityType);
            System.out.println("Role: " + role);
             String token = jwtService.generateToken(loginDTO.getEmail(), entityType, role);
             JwtResponse codeToken = new JwtResponse(token);
             codeToken.setCode(200);
             codeToken.setMessage("Success");
             codeToken.setStatus(true);
            // Generate and return the JWT token
            return codeToken;
        }

        // If authentication failed, return a failure response
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}

//        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());
//        Optional<Staff> optionalStaff = staffRepository.findByEmail(loginDTO.getEmail());
//
//        if (!optionalUser.isPresent() && !optionalStaff.isPresent()) {
//            throw new IllegalArgumentException("No such user or staff exists");
//        }
//
//        // Check for user login
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            if (user.getPassword().equals(loginDTO.getPassword())) {
//                return user;  // Login successful for user
//            }
//        }
//
//        // Check for staff login
//        if (optionalStaff.isPresent()) {
//            Staff staff = optionalStaff.get();
//            if (staff.getPassword().equals(loginDTO.getPassword())) {
//                return staff;  // Login successful for staff
//            }
//        }
//
//        // Fallback, though logically this should never be reached
//        throw new RuntimeException("Wrong email or password");
//    }}


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
