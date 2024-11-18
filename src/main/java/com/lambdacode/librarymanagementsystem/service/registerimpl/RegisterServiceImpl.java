package com.lambdacode.librarymanagementsystem.service.registerimpl;
import java.util.Optional;

import com.lambdacode.librarymanagementsystem.dto.RegisterDTO;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.RegisterService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private UserRepository userRepository;
@Autowired
private StaffRepository staffRepository;
    @Override
    public  User registerUser(RegisterDTO registerDTO) throws Exception {
        try{
            User user = new User();
            user.setId(registerDTO.getId());
            user.setAddress(registerDTO.getAddress());
            user.setName(registerDTO.getName());
            user.setPassword(encoder.encode(registerDTO.getPassword()));
            user.setEmail(registerDTO.getEmail());
            user.setPhone(registerDTO.getPhone());
            User existingUser = userRepository.findByEmail(registerDTO.getEmail());
            Staff existingStaff = staffRepository.findByEmail(registerDTO.getEmail());
            if (existingUser != null || existingStaff != null) {
                throw new Exception("Email already in use");
            }
            return userRepository.save(user);
        }catch(Exception e){
            throw new Exception("Error registering user");
        }

    }

    @Override
    public Staff registerStaff(RegisterDTO registerDTO) throws Exception{
        Staff staff = new Staff();
        staff.setId(registerDTO.getId());
        staff.setName(registerDTO.getName());
        staff.setPassword(encoder.encode(registerDTO.getPassword()));
        staff.setEmail(registerDTO.getEmail());
        staff.setPhone(registerDTO.getPhone());
        staff.setAddress(registerDTO.getAddress());
        staff.setBranch(registerDTO.getBranch());
        staff.setPosition(registerDTO.getPosition());
        User existingUser = userRepository.findByEmail(registerDTO.getEmail());
        Staff existingStaff = staffRepository.findByEmail(registerDTO.getEmail());
        if (existingUser != null || existingStaff != null) {
            throw new Exception("Email already in use");
        }
       return staffRepository.save(staff);
    }
}
