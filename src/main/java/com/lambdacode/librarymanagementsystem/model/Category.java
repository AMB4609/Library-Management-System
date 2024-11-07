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
@Entity(name = "Category")
public class Category {
    @Id
    @GeneratedValue
    private Long categoryId;

    private String categoryName;

    private String categoryDescription;

}
