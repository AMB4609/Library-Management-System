package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
//        logger.info("Received bookDTO: {}", bookDTO);
        try {
            bookService.addBook(bookDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
//            logger.error("Error adding book: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage()); // More descriptive error message
        }
    }

@PostMapping
    public ResponseEntity<Void> updateBookStatus(@RequestBody BookDTO bookDTO) {
    bookService.updateBookStatus(bookDTO);
    return ResponseEntity.ok().build();
}
}
