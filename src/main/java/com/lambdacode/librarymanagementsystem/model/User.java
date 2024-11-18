package com.lambdacode.librarymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_password")
    @JsonIgnore
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone")
    private Long phone;

    @Column(name = "user_address")
    private String address;

    private Integer loanCount;

}
