package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.MemberShipDTO;
import com.lambdacode.librarymanagementsystem.model.MemberShip;
import com.lambdacode.librarymanagementsystem.service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_USER;
import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.ROLE_LIBRARIAN;
import static com.lambdacode.librarymanagementsystem.constant.MemberShipConstant.*;

@RestController
@RequestMapping(MEMBERSHIP)
public class MemberShipController {
    @Autowired
    private MemberShipService memberShipService;

    @PostMapping(ADD_MEMBERSHIP)
    @PreAuthorize(ROLE_LIBRARIAN)
    public ResponseEntity<String> addMemberShip(@RequestBody MemberShipDTO memberShipDTO) {

        memberShipService.addMembership(memberShipDTO);
        return ResponseEntity.ok("Successfully added membership") ;
    }

    @PostMapping(PAYMENT_MEMBERSHIP)
    @PreAuthorize(ROLE_LIBRARIAN)
    public ResponseEntity<MemberShip> payment(@RequestBody MemberShipDTO memberShipDTO) {
       return ResponseEntity.ok().body(memberShipService.payment(memberShipDTO));
    }

    @GetMapping(GET_MEMBERSHIP)
    @PreAuthorize(ROLE_LIBRARIAN)
    public ResponseEntity<MemberShip> getMemberShip(@RequestBody MemberShipDTO memberShipDTO) {
            return ResponseEntity.ok().body(memberShipService.getMemberShip(memberShipDTO));
    }

    @DeleteMapping(DELETE_MEMBERSHIP)
    @PreAuthorize(ROLE_LIBRARIAN)
    public String deleteMemberShip(@RequestBody MemberShipDTO memberShipDTO) {
        memberShipService.deleteMemberShip(memberShipDTO);
        return "Successfully deleted membership";
    }

    @PutMapping(RENEW_MEMBERSHIP)
    @PreAuthorize(ROLE_LIBRARIAN)
    public String renewMemberShip(@RequestBody MemberShipDTO memberShipDTO) {
        memberShipService.renewMembership(memberShipDTO);
        return "Successfully renewed membership";
    }

    @GetMapping(GET_ALL_MEMBERSHIP)
    @PreAuthorize(ROLE_LIBRARIAN)
    public ResponseEntity<List<MemberShip>> getAllMemberShips() {
        return ResponseEntity.ok().body(memberShipService.getAllMembership());
    }
    @GetMapping(GET_MEMBERSHIP_BY_USER_ID)
    @PreAuthorize(HAS_ROLE_USER)
    public ResponseEntity<Object> getMemberShipByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok().body(memberShipService.getMemberShipByUserId(userEmail));
    }
}
