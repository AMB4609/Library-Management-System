package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.StaffDTO;
import com.lambdacode.librarymanagementsystem.model.Staff;
import com.lambdacode.librarymanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.StaffConstant.*;

@RestController
@RequestMapping(STAFF)
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PutMapping(UPDATE_STAFF)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Staff> updateStaff(@RequestBody StaffDTO staffDTO) {
            return staffService.updateStaff(staffDTO);
    }

    @DeleteMapping(DELETE_STAFF)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Void> deleteStaff(@RequestBody StaffDTO staffDTO) {
            staffService.deleteStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(DELETE_ALL_STAFF)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Void> deleteAllStaff() {
            staffService.deleteAllStaff();
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(GET_STAFF_BY_ID)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Staff> getStaffById(@RequestBody StaffDTO staffDTO) {
            return staffService.getStaffById(staffDTO);
    }

    @GetMapping(GET_ALL_STAFF)
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<List<Staff>> getAllStaff() {
            return staffService.getAllStaff();
    }
}

