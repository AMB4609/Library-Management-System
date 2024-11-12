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
        try {
            return staffService.updateStaff(staffDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/deleteStaff")
    public ResponseEntity<Void> deleteStaff(@RequestBody StaffDTO staffDTO) {
        try {
            staffService.deleteStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/deleteAllStaff")
    public ResponseEntity<Void> deleteAllStaff() {
        try {
            staffService.deleteAllStaff();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getStaffById")
    public ResponseEntity<Staff> getStaffById(@RequestBody StaffDTO staffDTO) {
        try {
            return staffService.getStaffById(staffDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getAllStaff")
    public ResponseEntity<List<Staff>> getAllStaff() {
        try {
            return staffService.getAllStaff();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

