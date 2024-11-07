package com.lambdacode.librarymanagementsystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @ManyToMany
    @JoinTable(name = "book_id")
    private List<Books> book;

    @ManyToOne
    @JoinColumn(name ="staff_id")
    private Staff staff;

    private String branchName;


    private String branchLocation;

    private String contact;

    private LocalTime openingTime;

    private LocalTime closingTime;

//    private Boolean isOpen;

    private int numberOfEmployees;

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
