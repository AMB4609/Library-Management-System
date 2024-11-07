package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
import com.lambdacode.librarymanagementsystem.model.Books;
import org.springframework.http.ResponseEntity;

import java.awt.print.Book;
import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO);

    ResponseEntity<Void> updateBookStatus(UpdateBookDTO updatebookDTO);

    void deleteBookById(BookDTO bookDTO);

    List getAllBooks();

    Books getBookById(BookDTO bookDTO);
}
