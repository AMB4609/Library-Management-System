package com.lambdacode.librarymanagementsystem.service.ReviewAndRating;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.exception.NoBookForLoanException;
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
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewAndRatingServiceImpl implements ReviewAndRatingService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public ReviewAndRating addReview(String userEmail,ReviewDTO reviewDTO){
        User user = validateUser(userEmail);
        if (reviewDTO.getRating() == null) {
            throw new NoRatingException("Please specify the rating also");
        }
        Book book = bookRepository.findById(reviewDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found for ID: " + reviewDTO.getBookId()));

        Optional<ReviewAndRating> existingReview = reviewRepository.findByBookAndUser(book, user);
        if (existingReview.isPresent()) {
            throw new NotYourReviewException("You have already reviewed this book!");
        }
        if(Objects.equals(reviewDTO.getBookId(), book.getBookId())){
            throw new NotYourReviewException("You are already reviewed!");
        }
        ReviewAndRating reviewAndRating = new ReviewAndRating();
        reviewAndRating.setReview(reviewDTO.getReview());
        reviewAndRating.setRating(reviewDTO.getRating());
        reviewAndRating.setReviewAndRatingId(reviewDTO.getReviewAndRatingId());
        reviewAndRating.setUser(user);
        reviewAndRating.setBook(book);
        reviewAndRating.setReviewDate(LocalDate.now());
        reviewAndRating=reviewRepository.save(reviewAndRating);
        book.setAverageRating(averageRating(reviewRepository.findByBook(book)));
        bookRepository.save(book);
        return reviewAndRating;
    }

    @Override
    public ReviewAndRating changeReview(String userEmail,ReviewDTO reviewDTO) {
        User user = validateUser(userEmail);

        ReviewAndRating reviewAndRating = reviewRepository.findById(Math.toIntExact(reviewDTO.getReviewAndRatingId()))
                .orElseThrow(()-> new NotFoundException("Review you are looking for is not found"));
        if (user != reviewAndRating.getUser()){
            throw new NotYourReviewException("this is not your review to change");
        }
        Book book = bookRepository.findById(reviewDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found for ID: " + reviewDTO.getBookId()));
        if (!book.getIsAvailable()) {
            throw new NoBookForLoanException("Book is not available for loan");
        }
        book.setAverageRating(averageRating(reviewRepository.findByBook(book)));
        bookRepository.save(book);
        reviewAndRating.setReview(reviewDTO.getReview());
        reviewAndRating.setRating(reviewDTO.getRating());
        reviewRepository.save(reviewAndRating);
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
    public ReviewAndRating toggleLikeToReview(String userEmail,ReviewDTO reviewDTO) {
        User user = validateUser(userEmail);
        Long reviewId = reviewDTO.getReviewAndRatingId(); // Ensure this method correctly retrieves the ID as a Long
        ReviewAndRating review = reviewRepository.findById(Math.toIntExact(reviewId))
                .orElseThrow(() -> new NotFoundException("Review not found"));
        if (review.getLikedUsers().contains(user)) {
            // Unlike the review
            review.getLikedUsers().remove(user);
            review.decrementLike(); // You need to implement this method
        } else {
            if(review.getDislikedUsers().contains(user)) {
                review.getDislikedUsers().remove(user);
                review.getLikedUsers().add(user);
                review.addLike();
            }else{
                review.getLikedUsers().add(user);
                review.addLike();
            }
            // Like the review
        }
        return reviewRepository.save(review);
    }

    @Override
    public ReviewAndRating toggleDislikeToReview(String userEmail, ReviewDTO reviewDTO) {
        // Retrieve the user by email
        User user = validateUser(userEmail);
        // Retrieve the review
        Long reviewId = reviewDTO.getReviewAndRatingId();
        ReviewAndRating review = reviewRepository.findById(Math.toIntExact(reviewId))
                .orElseThrow(() -> new NotFoundException("Review not found"));

        // Check if the user already disliked the review
        if (review.getDislikedUsers().contains(user)) {
            review.getDislikedUsers().remove(user);
            review.decrementDislike();
        } else {
            if (review.getLikedUsers().contains(user)) {
                review.getLikedUsers().remove(user);
                review.getDislikedUsers().add(user);
                review.decrementLike();
            }else{
                review.getDislikedUsers().add(user);
                review.addDislike();
            }

        }
        return reviewRepository.save(review);
    }
    private User validateUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new NotFoundException("Please login to add your review. If not registered, please contact the nearest Library!");
        }
        return user;
    }

}
