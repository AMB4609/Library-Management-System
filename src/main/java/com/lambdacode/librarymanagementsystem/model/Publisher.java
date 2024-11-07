package com.lambdacode.librarymanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity(name = "Publisher")

public class Publisher {

    @Id
    @GeneratedValue
    private Long publisherId;

    private String publisherName;

    private String publisherAddress;
}
