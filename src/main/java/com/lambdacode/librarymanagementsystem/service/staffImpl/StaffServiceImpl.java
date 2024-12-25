package com.lambdacode.librarymanagementsystem.service.staffImpl;

import com.lambdacode.librarymanagementsystem.dto.BaseDTO;
import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.dto.StaffDetailsDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateStaffDetailsDTO;
import com.lambdacode.librarymanagementsystem.exception.ApplicationException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private StaffRepository staffRepository;
    private BaseDTO setResponseFields(Object data, int code, String message, boolean status) {
        BaseDTO dto = new BaseDTO();
        dto.setCode(code);
        dto.setMessage(message);
        dto.setStatus(status);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResponseEntity<Staff> updateStaff(Long id, UpdateStaffDetailsDTO staffDTO) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Staff not found with ID: " + id));

        staff.setAddress(staffDTO.getStaffAddress());
        staff.setName(staffDTO.getStaffName());
        staff.setEmail(staffDTO.getStaffEmail());
        staff.setPhone(staffDTO.getStaffPhone());
        staff.setPassword(encoder.encode(staffDTO.getStaffPassword()));
        staffRepository.save(staff);
        return ResponseEntity.ok(staff);
    }

    @Override
    public ResponseEntity<Void> deleteStaff(StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(staffDTO.getStaffId())
                .orElseThrow(() -> new NotFoundException("Staff not found with ID: " + staffDTO.getStaffId()));
        staffRepository.delete(staff);
        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteAllStaff() {
        staffRepository.deleteAll();
    }

    @Override
    public BaseDTO getStaffById(String staffEmail) {
        Staff staff = staffRepository.findByEmail(staffEmail);
        if (staff == null) {
            throw new ApplicationException(404,"staff email not found");
        }
        StaffDetailsDTO staffDTO = new StaffDetailsDTO();
        staffDTO.setStaffId(staff.getId());
        staffDTO.setStaffName(staff.getName());
        staffDTO.setStaffEmail(staff.getEmail());
        staffDTO.setStaffPhone(staff.getPhone());
        staffDTO.setStaffAddress(staff.getAddress());
        staffDTO.setBranchId(staff.getBranch().getBranchId());
        staffDTO.setPosition(staff.getPosition());
        return setResponseFields(staffDTO,200,"staff retrieved successfully",true);
    }

    @Override
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffRepository.findAll());
    }
}
