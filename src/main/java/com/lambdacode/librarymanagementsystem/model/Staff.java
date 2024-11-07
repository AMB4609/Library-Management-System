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
    private Long StaffId;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    private String staffName;

    private String staffPassword;

    private String staffPhone;

    private String staffEmail;

    private String staffAddress;

    private String position;
}
