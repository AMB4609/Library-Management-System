package com.lambdacode.librarymanagementsystem.service.staffImpl;
import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.repository.StaffRepository;
import com.lambdacode.librarymanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public ResponseEntity<Staff> updateStaff(StaffDTO staffDTO) {
        Optional<Staff> staffDetails = staffRepository.findById(staffDTO.getStaffId());
        if (!staffDetails.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Staff staff = staffDetails.get();
        staff.setAddress(staffDTO.getStaffAddress());
        staff.setName(staffDTO.getStaffName());
        staff.setEmail(staffDTO.getStaffEmail());
        staff.setPhone(staffDTO.getStaffPhone());
        staffRepository.save(staff);
        return ResponseEntity.ok(staff);
    }

    @Override
    public ResponseEntity<Void> deleteStaff(StaffDTO staffDTO) {
        Optional<Staff> staffDetails = staffRepository.findById(staffDTO.getStaffId());
        if (!staffDetails.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        staffRepository.delete(staffDetails.get());
        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteAllStaff() {
        staffRepository.deleteAll();
    }

    @Override
    public ResponseEntity<Staff> getStaffById(StaffDTO staffDTO) {
        Optional<Staff> staffDetails = staffRepository.findById(staffDTO.getStaffId());
        if (staffDetails.isPresent()) {
            return ResponseEntity.ok(staffDetails.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffRepository.findAll());
    }
}
