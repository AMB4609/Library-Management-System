package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
import com.lambdacode.librarymanagementsystem.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO);

    ResponseEntity<Object> updateBookStatus(UpdateBookDTO updatebookDTO);

    void deleteBookById(BookDTO bookDTO);

    List<Book> getAllBooksWithReviews();

    Book getBookById(BookDTO bookDTO);

    UpdateBookDTO updateBookDetails(UpdateBookDTO updatebookDTO);
}
