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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserRepository userRepository;
@Autowired
private StaffRepository staffRepository;
    @Override
    public  User registerUser(RegisterDTO registerDTO) throws Exception {
        User user = new User();
        user.setUserId(registerDTO.getUserId());
        user.setUserName(registerDTO.getUserName());
        user.setUserPassword(registerDTO.getUserPassword());
        user.setUserEmail(registerDTO.getUserEmail());
        user.setUserPhone(registerDTO.getUserPhone());
        user.setUserAddress(registerDTO.getUserAddress());
        Optional<User> existingUser = userRepository.findByUserEmail(registerDTO.getUserEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Email already in use");
        }
        return userRepository.save(user);
    }

    @Override
    public Staff registerStaff(RegisterDTO registerDTO) throws Exception{
        Staff staff = new Staff();
        staff.setStaffId(registerDTO.getStaffId());
        staff.setBranch(registerDTO.getBranch());
        staff.setStaffName(registerDTO.getStaffName());
        staff.setStaffPassword(registerDTO.getStaffPassword());
        staff.setStaffEmail(registerDTO.getStaffEmail());
        staff.setStaffPhone(registerDTO.getStaffPhone());
        staff.setStaffAddress(registerDTO.getStaffAddress());
        staff.setPosition(registerDTO.getPosition());
       Optional<Staff> existingStaff = staffRepository.findByStaffEmail(registerDTO.getStaffEmail());
       if (existingStaff.isPresent()) {
           throw new Exception("Email already in use");
       }
       return staffRepository.save(staff);
    }
}
