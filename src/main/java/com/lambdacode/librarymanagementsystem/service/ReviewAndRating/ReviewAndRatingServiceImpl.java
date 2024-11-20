package com.lambdacode.librarymanagementsystem.service.ReviewAndRating;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.exception.NoRatingException;
import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.exception.NotYourReviewException;
import com.lambdacode.librarymanagementsystem.model.Book;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import com.lambdacode.librarymanagementsystem.model.User;
import com.lambdacode.librarymanagementsystem.repository.BookRepository;
import com.lambdacode.librarymanagementsystem.repository.ReviewRepository;
import com.lambdacode.librarymanagementsystem.repository.UserRepository;
import com.lambdacode.librarymanagementsystem.service.ReviewAndRatingService;
import jakarta.transaction.Transactional;
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

    public ReviewAndRating addReview(String userEmail,ReviewDTO reviewDTO){
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new NotFoundException("Please login to add your review, If not registered please contact nearest Library!");
        }
        if (reviewDTO.getRating() == null) {
            throw new NoRatingException("Please specify the rating also");
        }

        Book book = bookRepository.findById(reviewDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found for ID: " + reviewDTO.getBookId()));

        ReviewAndRating reviewAndRating = new ReviewAndRating();
        reviewAndRating.setReview(reviewDTO.getReview());
        reviewAndRating.setRating(reviewDTO.getRating());
        reviewAndRating.setReviewAndRatingId(reviewDTO.getReviewAndRatingId());
        reviewAndRating.setBook(book);
        reviewAndRating.setReviewDate(LocalDate.now());
        reviewAndRating=reviewRepository.save(reviewAndRating);
        book.setAverageRating(averageRating(reviewRepository.findByBook(book)));
        bookRepository.save(book);
        return reviewAndRating;
    }

    @Override
    public ReviewAndRating changeReview(String userEmail,ReviewDTO reviewDTO) {
        User user = userRepository.findByEmail(userEmail);

        ReviewAndRating reviewAndRating = reviewRepository.findById(Math.toIntExact(reviewDTO.getReviewAndRatingId()))
                .orElseThrow(()-> new NotFoundException("Review you are looking for is not found"));
        if (user != reviewAndRating.getUser()){
            throw new NotYourReviewException("this is not your review to change");
        }
        reviewAndRating.setReview(reviewDTO.getReview());
        reviewAndRating.setRating(reviewDTO.getRating());
        Book book = new Book();
        book.setAverageRating(averageRating(reviewRepository.findByBook(book)));
        bookRepository.save(book);
        return reviewAndRating;
    }
    public double averageRating(List<ReviewAndRating> reviews){
         double averageRating = reviews.stream()
                .mapToDouble(ReviewAndRating::getRating)
                .average()
                .orElse(0.0);
         return averageRating;
    }
    @Override
    public ReviewAndRating addLikeToReview(String userEmail,ReviewDTO reviewDTO) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new NotFoundException("Please login to add your review, If not registered please contact nearest Library!");
        }
        Long reviewId = reviewDTO.getReviewAndRatingId(); // Ensure this method correctly retrieves the ID as a Long
        ReviewAndRating review = reviewRepository.findById(Math.toIntExact(reviewId))
                .orElseThrow(() -> new NotFoundException("Review not found"));
        review.addLike();
        return reviewRepository.save(review);
    }
}
