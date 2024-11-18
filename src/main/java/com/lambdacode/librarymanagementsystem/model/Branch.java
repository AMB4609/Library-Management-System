package com.lambdacode.librarymanagementsystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "Branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

//    @ManyToMany(mappedBy = "branches")
//    private List<Book> books = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="staff_id")
    private Staff staff;

    private String branchName;


    private String branchLocation;

    private String contact;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private int numberOfEmployees;

    @Transient
    public boolean getIsOpen() {
        LocalTime now = LocalTime.now();
        return !now.isBefore(openingTime) && !now.isAfter(closingTime);
    }

//    public Boolean isOpen() {
//        LocalDate today = LocalDate.now();
//        DayOfWeek dayOfWeek = today.getDayOfWeek();
//
//        // Check if today is Saturday or Sunday
//        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
//            return false;  // Library is closed on weekends
//        }
//
//        // If it's a weekday, check the time
//        LocalTime now = LocalTime.now(); // gets the current time of your system
//        return !now.isBefore(OpeningTime) && !now.isAfter(ClosingTime);
//    }
}
