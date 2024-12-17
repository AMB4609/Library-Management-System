package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BaseDTO;
import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
import com.lambdacode.librarymanagementsystem.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO);

    ResponseEntity<Object> updateBookStatus(UpdateBookDTO updatebookDTO);

    void deleteBookById(Long bookId);

    BaseDTO getAllBooksWithReviews(Pageable pageable);

    BaseDTO getBookById(long bookId);

    UpdateBookDTO updateBookDetails(UpdateBookDTO updatebookDTO);
}
