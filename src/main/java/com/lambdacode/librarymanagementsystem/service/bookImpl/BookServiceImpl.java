package com.lambdacode.librarymanagementsystem.service.bookImpl;

import com.lambdacode.librarymanagementsystem.dto.BookDTO;
import com.lambdacode.librarymanagementsystem.dto.UpdateBookDTO;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.model.Author;
import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.model.Category;
import com.lambdacode.librarymanagementsystem.repository.AuthorRepository;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.CategoryRepository;
import com.lambdacode.librarymanagementsystem.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addBook(BookDTO bookDTO) throws IllegalArgumentException, NotFoundException {
        if (bookDTO.getAuthorId() == null || bookDTO.getPublisherName() == null || bookDTO.getCategoryId() == null) {
            throw new NotFoundException("Author ID, Publisher Name, and Category ID must not be null.");
        }
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found for ID: " + bookDTO.getAuthorId()));
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found for ID: " + bookDTO.getCategoryId()));

        Book books = new Book();
        books.setAuthor(author);
        books.setPublisher(bookDTO.getPublisherName());
        books.setCategory(category);
        books.setBookName(bookDTO.getBookName());
        books.setBooksAvailable(bookDTO.getBooksAvailable());
        books.setISBN(bookDTO.getISBN());
        books.setReleaseDate(bookDTO.getReleaseDate());

        bookRepository.save(books);
    }

    @Override
    public ResponseEntity<Object> updateBookStatus(UpdateBookDTO updateBookDTO) {
        return bookRepository.findById(updateBookDTO.getBookId())
                .map(book -> {
                    book.setBooksAvailable(updateBookDTO.getBooksAvailable());
                    bookRepository.save(book);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public void deleteBookById(BookDTO bookDTO) throws NoSuchElementException {
        Book book = bookRepository.findById(bookDTO.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found for ID: " + bookDTO.getBookId()));
        bookRepository.delete(book);
    }

//    @Override
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }

    @Override
    public Book getBookById(long bookId) throws NoSuchElementException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found for ID: " + bookId));
    }

    @Override
    public UpdateBookDTO updateBookDetails(UpdateBookDTO updatebookDTO) {
        Book updateBook = bookRepository.findById(updatebookDTO.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found for ID: "));
        if (updatebookDTO.getAuthorId() == null || updatebookDTO.getPublisherName() == null || updatebookDTO.getCategoryId() == null) {
            throw new NotFoundException("Author ID, Publisher Name, and Category ID must not be null.");
        }
        Author author = authorRepository.findById(updatebookDTO.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found for ID: " + updatebookDTO.getAuthorId()));
        Category category = categoryRepository.findById(updatebookDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found for ID: " + updatebookDTO.getCategoryId()));
        updateBook.setBookName(updateBook.getBookName());
        updateBook.setBookDescription(updateBook.getBookDescription());
        updateBook.setAuthor(author);
        updateBook.setCategory(category);
        updateBook.setPublisher(updatebookDTO.getPublisherName());
        updateBook.setBooksAvailable(updatebookDTO.getBooksAvailable());
        updateBook.setISBN(updatebookDTO.getISBN());
        bookRepository.save(updateBook);
        return updatebookDTO;
    }

    @Transactional
    public List<Book> getAllBooksWithReviews() {
        return bookRepository.findAllBooksWithReviews();
    }
}
