package com.lambdacode.librarymanagementsystem.service.staffImpl;

import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.exception.NoRatingException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public ResponseEntity<Staff> updateStaff(StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(staffDTO.getStaffId())
                .orElseThrow(() -> new NotFoundException("Staff not found with ID: " + staffDTO.getStaffId()));

        staff.setAddress(staffDTO.getStaffAddress());
        staff.setName(staffDTO.getStaffName());
        staff.setEmail(staffDTO.getStaffEmail());
        staff.setPhone(staffDTO.getStaffPhone());
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
    public ResponseEntity<Staff> getStaffById(StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(staffDTO.getStaffId())
                .orElseThrow(() -> new NotFoundException("Staff not found with ID: " + staffDTO.getStaffId()));
        return ResponseEntity.ok(staff);
    }

    @Override
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffRepository.findAll());
    }
}
