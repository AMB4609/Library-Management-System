package com.lambdacode.librarymanagementsystem.mapper;

import com.lambdacode.librarymanagementsystem.dto.BookByIdDTO;
import com.lambdacode.librarymanagementsystem.dto.GetAllBookDTO;
import com.lambdacode.librarymanagementsystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BookMapper{

    public List<GetAllBookDTO> getAllBookDTOs(List<Book> books) {
        return books.stream()
                .map(this::toGetAllBookDTO)
                .collect(Collectors.toList());
    }

    private GetAllBookDTO toGetAllBookDTO(Book book) {
        GetAllBookDTO dto = new GetAllBookDTO();
        dto.setBookId(book.getBookId());
        dto.setBookName(book.getBookName());
        dto.setBooksAvailable(book.getBooksAvailable());
        dto.setCategoryName(book.getCategory().getCategoryName());
        dto.setAverageRating(book.getAverageRating());
        dto.setReleaseDate(book.getReleaseDate());
        return dto;
    }

    public BookByIdDTO BookToBookDTO(Book book) {
        BookByIdDTO dto = new BookByIdDTO();
        dto.setBookId(book.getBookId());
        dto.setBookName(book.getBookName());
        dto.setBooksAvailable(book.getBooksAvailable());
        dto.setISBN(book.getISBN());
        dto.setBookDescription(book.getBookDescription());
        dto.setAuthorId(book.getAuthor().getAuthorId());
        dto.setPublisher(book.getPublisher());
        dto.setCategoryId(book.getCategory().getCategoryId());
        dto.setReleaseDate(book.getReleaseDate());
        dto.setAuthorName(book.getAuthor().getAuthorName());
        dto.setCategoryName(book.getCategory().getCategoryName());
        dto.setIsAvailable(book.getIsAvailable());
        dto.setAverageRating(book.getAverageRating());
        dto.setReviewAndRating(book.getReviews());
        return dto;
    }
}
