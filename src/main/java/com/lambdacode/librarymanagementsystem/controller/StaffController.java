package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PutMapping("/updateStaff")
    public ResponseEntity<Staff> updateStaff(@RequestBody StaffDTO staffDTO) {
            return staffService.updateStaff(staffDTO);
    }

    @DeleteMapping("/deleteStaff")
    public ResponseEntity<Void> deleteStaff(@RequestBody StaffDTO staffDTO) {
            staffService.deleteStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllStaff")
    public ResponseEntity<Void> deleteAllStaff() {
            staffService.deleteAllStaff();
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getStaffById")
    public ResponseEntity<Staff> getStaffById(@RequestBody StaffDTO staffDTO) {
            return staffService.getStaffById(staffDTO);
    }

    @GetMapping("/getAllStaff")
    public ResponseEntity<List<Staff>> getAllStaff() {
            return staffService.getAllStaff();
    }
}

