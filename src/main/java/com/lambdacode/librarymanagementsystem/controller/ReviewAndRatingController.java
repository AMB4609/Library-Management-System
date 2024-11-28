package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import com.lambdacode.librarymanagementsystem.service.ReviewAndRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_USER;
import static com.lambdacode.librarymanagementsystem.constant.ReviewAndRatingConstant.*;

@RestController
@RequestMapping(REVIEW_AND_RATING)
public class ReviewAndRatingController {
    @Autowired
    private ReviewAndRatingService reviewAndRatingService;

    @PostMapping(REVIEW_AND_RATING_ADD)
    @PreAuthorize(HAS_ROLE_USER)
    public ResponseEntity<ReviewAndRating> addReviewAndRating(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
            return ResponseEntity.ok(reviewAndRatingService.addReview(userEmail ,reviewDTO)) ;
    }
    @PutMapping(REVIEW_AND_RATING_UPDATE)
    @PreAuthorize(HAS_ROLE_USER)
    public ResponseEntity<ReviewAndRating> changeReviewAndRating(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(reviewAndRatingService.changeReview(userEmail,reviewDTO));
    }
    @PostMapping(TOGGLE_LIKE_TO_REVIEW)
    public ResponseEntity<ReviewAndRating> toggleLikeToReview(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(reviewAndRatingService.toggleLikeToReview(userEmail,reviewDTO));
    }
    @PostMapping(TOGGLE_DISLIKE_TO_REVIEW)
    public ResponseEntity<ReviewAndRating> toggleDisLikeToReview(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(reviewAndRatingService.toggleDislikeToReview(userEmail, reviewDTO));
    }
}
