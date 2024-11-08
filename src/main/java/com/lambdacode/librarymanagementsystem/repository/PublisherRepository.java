package com.lambdacode.librarymanagementsystem.repository;

import com.lambdacode.librarymanagementsystem.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
    Optional<Publisher> findByPublisherName(String PublisherName);
}
