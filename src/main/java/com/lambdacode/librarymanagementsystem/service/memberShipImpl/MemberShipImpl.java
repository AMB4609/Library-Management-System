package com.lambdacode.librarymanagementsystem.service.memberShipImpl;

import com.lambdacode.librarymanagementsystem.dto.MemberShipDTO;
import com.lambdacode.librarymanagementsystem.exception.AlreadyExistsException;
import com.lambdacode.librarymanagementsystem.exception.ExceededPaymentException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.exception.PayFirstException;
import com.lambdacode.librarymanagementsystem.model.MemberShip;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.MemberShipRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class  MemberShipImpl implements MemberShipService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private MemberShipRepository memberShipRepository;


    @Override
    public void addMembership(MemberShipDTO memberShipDTO) {
        User user = userRepository.findByEmail(memberShipDTO.getUserEmail());
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        // Check if membership exists for this user ID
        memberShipRepository.findByUserId(user.getId())
                .ifPresent(m -> {
                    throw new AlreadyExistsException("Membership of the user already exists");
                });
        if(memberShipDTO.getCostPerMonth()*memberShipDTO.getMonthsOfMembership() < memberShipDTO.getPayedAmount()){
            throw new ExceededPaymentException("You have exceeded the amount you need to pay");
        }
        MemberShip membership = new MemberShip();
        membership.setMonthsOfMembership(memberShipDTO.getMonthsOfMembership());
        membership.setMemberShipDate(LocalDate.now());
        membership.setMemberShipExpiry(LocalDate.now().plusMonths(memberShipDTO.getMonthsOfMembership()));
        membership.setUser(user);
        membership.setCostPerMonth(memberShipDTO.getCostPerMonth());
        membership.setPayedAmount(memberShipDTO.getPayedAmount());
        membership.setPayableAmount(memberShipDTO.getCostPerMonth() * memberShipDTO.getMonthsOfMembership() - memberShipDTO.getPayedAmount());

        memberShipRepository.save(membership);
    }

    @Override
    public MemberShip payment(MemberShipDTO memberShipDTO) {
        MemberShip membership = memberShipRepository.findById(memberShipDTO.getMemberShipId())
                .orElseThrow(() -> new NotFoundException("Membership not found"));
        User user = userRepository.findByEmail(membership.getUser().getEmail());
        if (user == null) {
            throw new NotFoundException("User you are trying to add Payments not found");
        }

        if(memberShipDTO.getPayedAmount()>membership.getPayableAmount()){
            throw new ExceededPaymentException("You have exceeded the amount you need to pay");
        }
        membership.setPayedAmount(membership.getPayedAmount()+memberShipDTO.getPayedAmount());
        membership.setPayableAmount(membership.getPayableAmount()-memberShipDTO.getPayedAmount());

        return memberShipRepository.save(membership);
    }

    @Override
    public void renewMembership(MemberShipDTO memberShipDTO) {
        MemberShip membership = memberShipRepository.findById(memberShipDTO.getMemberShipId())
                .orElseThrow(() -> new NotFoundException("Membership not found"));
        if (membership.getPayableAmount()> 0){
            throw new PayFirstException("First pay your payable amount to renew membership");
        }
        if(memberShipDTO.getCostPerMonth()*memberShipDTO.getMonthsOfMembership() < memberShipDTO.getPayedAmount()){
            throw new ExceededPaymentException("You have exceeded the amount you need to pay");
        }
        membership.setMonthsOfMembership(memberShipDTO.getMonthsOfMembership());
        membership.setMemberShipDate(LocalDate.now());
        membership.setMemberShipExpiry(LocalDate.now().plusMonths(memberShipDTO.getMonthsOfMembership()));
        membership.setCostPerMonth(memberShipDTO.getCostPerMonth());
        membership.setPayedAmount(memberShipDTO.getPayedAmount());
        membership.setPayableAmount((memberShipDTO.getCostPerMonth()*memberShipDTO.getMonthsOfMembership())-memberShipDTO.getPayedAmount());
        memberShipRepository.save(membership);
    }

    @Override
    public MemberShip getMemberShip(MemberShipDTO memberShipDTO) {
        MemberShip membership = memberShipRepository.findById(memberShipDTO.getMemberShipId())
                .orElseThrow(() -> new NotFoundException("Membership not found"));
        return membership;
    }

    @Override
    public List<MemberShip> getAllMembership() {
        return memberShipRepository.findAll();
    }

    @Override
    public MemberShip getMemberShipByUserId(String userEmail) {
        MemberShip membership = memberShipRepository.findByUserEmail(userEmail);
        return membership;
    }

    @Override
    public void deleteMemberShip(MemberShipDTO memberShipDTO) {
        memberShipRepository.deleteById(memberShipDTO.getMemberShipId());
    }
}
