package com.lambdacode.librarymanagementsystem.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue
    @Column(name = "staff_id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    @Column(name = "staff_name")
    private String name;
    @Column(name = "staff_password")
    private String password;
    @Column(name = "staff_phone")
    private Long    phone;
    @Column(name = "staff_email")
    private String email;
    @Column(name = "staff_address")
    private String address;
    @Column(name = "position")
    private String position;
}
