package com.lambdacode.librarymanagementsystem.service.authorimpl;

import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.repository.AuthorRepository;
import com.lambdacode.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(Author author) {
    return authorRepository.findById(author.getAuthorId())
            .orElseThrow(() -> new NotFoundException("Author not found"));
    }

    @Override
    public List<Author> getAllAuthors() {
        try{
            return authorRepository.findAll();
        }catch (Exception e){
            throw new NotFoundException("No Authors found");
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        authorRepository.deleteById(author.getAuthorId());
    }
}
