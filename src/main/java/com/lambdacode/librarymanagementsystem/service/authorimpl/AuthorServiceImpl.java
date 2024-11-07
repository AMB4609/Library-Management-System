package com.lambdacode.librarymanagementsystem.service.authorimpl;

import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.repository.AuthorRepository;
import com.lambdacode.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
}
