package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addBook")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
            bookService.addBook(bookDTO);
            return ResponseEntity.ok().build();
    }

@PutMapping("/updateBookStatus")
    public ResponseEntity<Void> updateBookStatus(@RequestBody UpdateBookDTO updatebookDTO) {
    bookService.updateBookStatus(updatebookDTO);
    return ResponseEntity.ok().build();
}
    @DeleteMapping("/deleteBookById")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_LIBRARIAN')")
    public ResponseEntity<Void> deleteBookByID(@RequestBody BookDTO bookDTO) {
            bookService.deleteBookById(bookDTO);
            return ResponseEntity.ok().build();

    }
    @GetMapping("/getAllBooks")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
            List<BookDTO> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);

    }
    @GetMapping("/getBookById")

    public ResponseEntity<Book> getBookByID(@RequestBody BookDTO bookDTO) {
           Book books =  bookService.getBookById(bookDTO);
            return ResponseEntity.ok(books);
    }
}
