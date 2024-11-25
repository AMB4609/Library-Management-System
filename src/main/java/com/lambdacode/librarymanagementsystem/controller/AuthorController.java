package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.service.AuthorService;
import com.lambdacode.librarymanagementsystem.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<Void> addAuthor(@RequestBody Author author) {
            authorService.addAuthor(author);
            return ResponseEntity.ok().build();
    }
    @GetMapping("/getAuthorById")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<Author> getAuthorById(@RequestBody Author author) {

        return ResponseEntity.ok().body(authorService.getAuthorById(author));
    }
    @GetMapping("/getAllAuthors")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok().body(authors);
    }
    @DeleteMapping("/deleteAuthor")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<String> deleteAuthor(@RequestBody Author author) {
        authorService.deleteAuthor(author);
         return ResponseEntity.ok().body("Author Deleted!");
    }
}
