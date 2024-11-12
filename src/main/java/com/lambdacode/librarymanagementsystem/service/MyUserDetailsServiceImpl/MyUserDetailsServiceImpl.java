package com.lambdacode.librarymanagementsystem.service.MyUserDetailsServiceImpl;

import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.StaffPrinciple;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.model.UserPrinciple;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Try to find a staff or user entity by email.
        Staff staff = staffRepository.findByEmail(email);
        if (staff != null) {
            return new StaffPrinciple(staff);
        }

        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new UserPrinciple(user);
        }

        // If neither a staff nor a user is found, throw an exception.
        // To avoid giving specific details about which part of the authentication failed,
        // a generic error message is used.
        throw new UsernameNotFoundException("Authentication failed.");
    }
}

