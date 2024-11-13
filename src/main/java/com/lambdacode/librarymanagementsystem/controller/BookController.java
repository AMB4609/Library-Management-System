package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
import com.lambdacode.librarymanagementsystem.model.Books;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addBook")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
        try {
            bookService.addBook(bookDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // More descriptive error message
        }
    }

@PutMapping("/updateBookStatus")
    public ResponseEntity<Void> updateBookStatus(@RequestBody UpdateBookDTO updatebookDTO) {
    bookService.updateBookStatus(updatebookDTO);
    return ResponseEntity.ok().build();
}
    @DeleteMapping("/deleteBookById")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_LIBRARIAN')")
    public ResponseEntity<Void> deleteBookByID(@RequestBody BookDTO bookDTO) {
        try{
            bookService.deleteBookById(bookDTO);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/getAllBooks")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        try{
            List<BookDTO> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/getBookById")

    public ResponseEntity<Books> getBookByID(@RequestBody BookDTO bookDTO) {
        try{
           Books books =  bookService.getBookById(bookDTO);
            return ResponseEntity.ok(books);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
