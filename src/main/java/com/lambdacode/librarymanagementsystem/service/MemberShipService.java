package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.MemberShipDTO;
import com.lambdacode.librarymanagementsystem.model.MemberShip;

import java.util.List;

public interface MemberShipService {
    void addMembership(MemberShipDTO memberShipDTO);

    MemberShip payment(MemberShipDTO memberShipDTO);

    void deleteMemberShip(MemberShipDTO memberShipDTO);
    
    void renewMembership(MemberShipDTO memberShipDTO);

    MemberShip getMemberShip(MemberShipDTO memberShipDTO);

    List<MemberShip> getAllMembership();

    MemberShip getMemberShipByUserId(String userEmail);
}
