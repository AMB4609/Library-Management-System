package com.lambdacode.librarymanagementsystem.service.bookImpl;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.mapper.BookMapper;
import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.model.Books;
import com.lambdacode.librarymanagementsystem.model.Category;
import com.lambdacode.librarymanagementsystem.model.Publisher;
import com.lambdacode.librarymanagementsystem.repository.AuthorRepository;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.CategoryRepository;
import com.lambdacode.librarymanagementsystem.repository.PublisherRepository;
import com.lambdacode.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void addBook(BookDTO bookDTO) throws NoSuchElementException, IllegalArgumentException {
        if (bookDTO.getAuthorId() == null || bookDTO.getPublisherName() == null || bookDTO.getCategoryId() == null) {
            throw new IllegalArgumentException("Author ID, Publisher ID, and Category ID must not be null.");
        }
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException("Author not found for ID: " + bookDTO.getAuthorId()));
        Publisher publisher = publisherRepository.findByPublisherName(bookDTO.getPublisherName())
                .orElseThrow(() -> new NoSuchElementException("Publisher not found for name: " + bookDTO.getPublisherName()));
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found for ID: " + bookDTO.getCategoryId()));

        Books books = new Books();
        books.setBookId(bookDTO.getBookId());
        books.setAuthor(author);
        books.setPublisher(publisher);
        books.setCategory(category);
        books.setBookName(bookDTO.getBookName());
        books.setBooksAvailable(bookDTO.getBooksAvailable());
        books.setISBN(bookDTO.getISBN());
        books.setStatus(bookDTO.getStatus());
        books.setReleaseDate(bookDTO.getReleaseDate());

        bookRepository.save(books);
    }

    @Override
    public ResponseEntity<Void> updateBookStatus(BookDTO bookDTO) {
        Books books = new Books();
        books.setBookId(bookDTO.getBookId());
        books.setBooksAvailable(bookDTO.getBooksAvailable());
        books.setStatus(bookDTO.getStatus());
        if(books.booksAvailable > 1){
            bookRepository.save(books);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
