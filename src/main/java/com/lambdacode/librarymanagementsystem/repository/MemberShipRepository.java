package com.lambdacode.librarymanagementsystem.repository;

import com.lambdacode.librarymanagementsystem.model.MemberShip;
import com.lambdacode.librarymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberShipRepository extends JpaRepository<MemberShip, Integer> {
    Optional<MemberShip> findByUserId(Long id);
}
