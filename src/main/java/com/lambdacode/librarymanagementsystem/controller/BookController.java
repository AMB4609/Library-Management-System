package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_LIBRARIAN_OR_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.BookConstant.*;

@RestController
@RequestMapping(BOOK)
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping(ADD_BOOK)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
            bookService.addBook(bookDTO);
            return ResponseEntity.ok().build();
    }

    @PutMapping(UPDATE_BOOK_STATUS)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<Void> updateBookStatus(@RequestBody UpdateBookDTO updatebookDTO) {
    bookService.updateBookStatus(updatebookDTO);
    return ResponseEntity.ok().build();
}
    @DeleteMapping(DELETE_BOOK_BY_ID)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<Void> deleteBookByID(@RequestBody BookDTO bookDTO) {
            bookService.deleteBookById(bookDTO);
            return ResponseEntity.ok().build();

    }
    @GetMapping(GET_ALL_BOOKS)
    public ResponseEntity<List<Book>> getAllBooksWithReviews() {
            List<Book> books = bookService.getAllBooksWithReviews();
            return ResponseEntity.ok(books);

    }
    @GetMapping(GET_BOOK_BY_ID)
    public ResponseEntity<Book> getBookByID(@PathVariable long bookId) {
           Book books =  bookService.getBookById(bookId);
            return ResponseEntity.ok(books);
    }
    @PutMapping(UPDATE_BOOK_DETAILS)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<UpdateBookDTO> updateBookDetails(@RequestBody UpdateBookDTO updatebookDTO) {
        bookService.updateBookDetails(updatebookDTO);
        return ResponseEntity.ok().body(updatebookDTO);
    }
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_LIBRARIAN')")
//    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN', 'ROLE_USER')")
}
