package com.lambdacode.librarymanagementsystem.repository;

import com.lambdacode.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT DISTINCT b FROM books b LEFT JOIN FETCH b.reviews")
    List<Book> findAllBooksWithReviews();
}
