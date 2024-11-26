package com.lambdacode.librarymanagementsystem.repository;

import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import com.lambdacode.librarymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewAndRating, Integer> {
    List<ReviewAndRating> findByBook(Book book);

    Optional<ReviewAndRating> findByBookAndUser(Book book, User user);
}
