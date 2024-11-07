package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private Long userPhone;

    private String userAddress;

}
