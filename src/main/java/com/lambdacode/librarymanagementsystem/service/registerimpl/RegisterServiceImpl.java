package com.lambdacode.librarymanagementsystem.service.registerimpl;

import com.lambdacode.librarymanagementsystem.dto.RegisterDTO;
import com.lambdacode.librarymanagementsystem.exception.AlreadyExistsException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.model.Branch;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.BranchRepository;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private UserRepository userRepository;
@Autowired
private StaffRepository staffRepository;
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public  User registerUser(RegisterDTO registerDTO){
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
                throw new AlreadyExistsException("Email already in use");
            }
            return userRepository.save(user);

    }

    @Override
    public Staff registerStaff(RegisterDTO registerDTO){
        Branch branch = branchRepository.findById(registerDTO.getBranchId()).orElseThrow(
                () -> new NotFoundException("Branch not found")
        );
        Staff staff = new Staff();
        staff.setId(registerDTO.getId());
        staff.setName(registerDTO.getName());
        staff.setPassword(encoder.encode(registerDTO.getPassword()));
        staff.setEmail(registerDTO.getEmail());
        staff.setPhone(registerDTO.getPhone());
        staff.setAddress(registerDTO.getAddress());
        staff.setBranch(branch);
        staff.setPosition(registerDTO.getPosition());
        User existingUser = userRepository.findByEmail(registerDTO.getEmail());
        Staff existingStaff = staffRepository.findByEmail(registerDTO.getEmail());
        if (existingUser != null || existingStaff != null) {
            throw new AlreadyExistsException("Email already in use");
        }
       return staffRepository.save(staff);
    }
}
