package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {

    ResponseEntity<Staff> updateStaff(StaffDTO staffDTO);

    ResponseEntity<Void> deleteStaff(StaffDTO staffDTO);

    void deleteAllStaff();

    ResponseEntity<Staff> getStaffById(StaffDTO staffDTO);

    ResponseEntity<List<Staff>> getAllStaff();
}
