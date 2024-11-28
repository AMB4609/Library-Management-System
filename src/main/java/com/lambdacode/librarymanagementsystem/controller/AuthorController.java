package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorConstant.*;
import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_LIBRARIAN_OR_ADMIN;

@RestController
@RequestMapping(AUTHOR)
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping(ADD_AUTHOR)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<Void> addAuthor(@RequestBody Author author) {
            authorService.addAuthor(author);
            return ResponseEntity.ok().build();
    }
    @GetMapping(GET_AUTHOR)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<Author> getAuthorById(@RequestBody Author author) {

        return ResponseEntity.ok().body(authorService.getAuthorById(author));
    }
    @GetMapping(GET_ALL_AUTHOR)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok().body(authors);
    }
    @DeleteMapping(DELETE_AUTHOR)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<String> deleteAuthor(@RequestBody Author author) {
        authorService.deleteAuthor(author);
         return ResponseEntity.ok().body("Author Deleted!");
    }
}
