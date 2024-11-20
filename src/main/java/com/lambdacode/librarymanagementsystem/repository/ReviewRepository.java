package com.lambdacode.librarymanagementsystem.repository;

import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewAndRating, Integer> {
    List<ReviewAndRating> findByBook(Book book);
}
