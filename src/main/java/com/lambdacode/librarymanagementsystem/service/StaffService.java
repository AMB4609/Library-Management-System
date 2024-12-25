package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BaseDTO;
import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateStaffDetailsDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {

    ResponseEntity<Staff> updateStaff(Long id, UpdateStaffDetailsDTO staffDTO);

    ResponseEntity<Void> deleteStaff(StaffDTO staffDTO);

    void deleteAllStaff();

    BaseDTO getStaffById(String staffEmail);

    ResponseEntity<List<Staff>> getAllStaff();
}
