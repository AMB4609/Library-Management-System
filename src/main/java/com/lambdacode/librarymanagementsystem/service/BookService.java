package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import org.springframework.http.ResponseEntity;

public interface BookService {
    void addBook(BookDTO bookDTO);

    ResponseEntity<Void> updateBookStatus(BookDTO bookDTO);
}
