package com.lambdacode.librarymanagementsystem.service.bookImpl;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
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

import java.awt.print.Book;
import java.util.List;
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
    public ResponseEntity<Void> updateBookStatus(UpdateBookDTO updatebookDTO) {

        Optional<Books> booksOptional = bookRepository.findById(updatebookDTO.getBookId());
        Books books = booksOptional.get();
        if(booksOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
       // if(books.getBookId().equals(updatebookDTO.getBookId())){
          //  Books updatebooks = new Books();

            books.setBooksAvailable(updatebookDTO.getBooksAvailable());
            if(books.getBooksAvailable() > 1){
                books.setStatus(Boolean.FALSE);
            }
            bookRepository.save(books);
            return ResponseEntity.ok().build();
        }

    @Override
    public void deleteBookById(BookDTO bookDTO) {
        Optional<Books> booksOptional = bookRepository.findById(bookDTO.getBookId());
        Books books = booksOptional.get();
        if(booksOptional.isEmpty()){
            throw new NoSuchElementException("Book not found for ID: " + bookDTO.getBookId());
        }
        bookRepository.delete(books);
    }

    @Override
    public List getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books getBookById(BookDTO bookDTO) {
        Optional<Books> booksOptional = bookRepository.findById(bookDTO.getBookId());
        Books books = booksOptional.get();
        if(!booksOptional.isPresent()){
            throw new NoSuchElementException("Book not found for ID: " + bookDTO.getBookId());
        }
        return books;
    }

}

