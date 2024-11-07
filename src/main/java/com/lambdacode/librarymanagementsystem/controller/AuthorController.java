package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<Void> addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return ResponseEntity.ok().build();
    }


}
