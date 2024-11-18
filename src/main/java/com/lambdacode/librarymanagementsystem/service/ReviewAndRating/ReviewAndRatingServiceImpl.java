package com.lambdacode.librarymanagementsystem.service.ReviewAndRating;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.exception.NoRatingException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.ReviewRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.ReviewAndRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewAndRatingServiceImpl implements ReviewAndRatingService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public ReviewAndRating addReview(ReviewDTO reviewDTO){
        if (reviewDTO.getRating() == null) {
            throw new NoRatingException("Please specify the rating also");
        }

        Book book = bookRepository.findById(reviewDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found for ID: " + reviewDTO.getBookId()));
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + reviewDTO.getUserId()));

        ReviewAndRating reviewAndRating = new ReviewAndRating();
        reviewAndRating.setReview(reviewDTO.getReview());
        reviewAndRating.setRating(reviewDTO.getRating());
        reviewAndRating.setReviewAndRatingId(reviewDTO.getReviewAndRatingId());
        reviewAndRating.setUser(user);
        reviewAndRating.setBooks(book);
        reviewAndRating.setReviewDate(LocalDate.now());
//        List<ReviewAndRating> reviews = reviewRepository.findByBooks(book);
//        reviews.add(reviewAndRating); // Include the new review in the calculation
//        double averageRating = reviews.stream()
//                .mapToDouble(ReviewAndRating::getRating)
//                .average()
//                .orElse(0.0);
        book.setAverageRating(averageRating(reviewRepository.findByBooks(book)));
        bookRepository.save(book);
        return reviewRepository.save(reviewAndRating);
    }

    @Override
    public ReviewAndRating changeReview(ReviewDTO reviewDTO) {
        ReviewAndRating reviewAndRating = reviewRepository.findById((int) reviewDTO.getReviewAndRatingId())
                .orElseThrow(()-> new NotFoundException("Review you are looking for is not found"));
        reviewAndRating.setReview(reviewDTO.getReview());
        reviewAndRating.setRating(reviewDTO.getRating());
        Book book = new Book();
        book.setAverageRating(averageRating(reviewRepository.findByBooks(book)));
        bookRepository.save(book);
        return null;
    }
    public double averageRating(List<ReviewAndRating> reviews){
         double averageRating = reviews.stream()
                .mapToDouble(ReviewAndRating::getRating)
                .average()
                .orElse(0.0);
         return averageRating;
    }

}
