package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.model.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    void addAuthor(Author author);

    Author getAuthorById(Author author);

    List<Author> getAllAuthors();

    void deleteAuthor(Author author);
}
