package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.MemberShipDTO;
import com.lambdacode.librarymanagementsystem.model.MemberShip;
import com.lambdacode.librarymanagementsystem.service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership")
public class MemberShipController {
    @Autowired
    private MemberShipService memberShipService;

    @PostMapping("/addMemberShip")
    public ResponseEntity<String> addMemberShip(@RequestBody MemberShipDTO memberShipDTO) {
        memberShipService.addMembership(memberShipDTO);
        return ResponseEntity.ok("Successfully added membership") ;
    }

    @PostMapping("/paymentMembership")
    public ResponseEntity<MemberShip> payment(@RequestBody MemberShipDTO memberShipDTO) {
       return ResponseEntity.ok().body(memberShipService.payment(memberShipDTO));
    }

    @GetMapping("/getMemberShip")
    public ResponseEntity<MemberShip> getMemberShip(@RequestBody MemberShipDTO memberShipDTO) {
            return ResponseEntity.ok().body(memberShipService.getMemberShip(memberShipDTO));
    }

    @DeleteMapping("/deleteMemberShip")
    public String deleteMemberShip(@RequestBody MemberShipDTO memberShipDTO) {
        memberShipService.deleteMemberShip(memberShipDTO);
        return "Successfully deleted membership";
    }

    @PutMapping("/renewMemberShip")
    public String renewMemberShip(@RequestBody MemberShipDTO memberShipDTO) {
        memberShipService.renewMembership(memberShipDTO);
        return "Successfully renewed membership";
    }

    @GetMapping("/getAllMemberShips")
    public ResponseEntity<List<MemberShip>> getAllMemberShips() {
        return ResponseEntity.ok().body(memberShipService.getAllMembership());
    }
}
