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

@RestController
@RequestMapping("/api/reviewAndRating")
public class ReviewAndRatingController {
    @Autowired
    private ReviewAndRatingService reviewAndRatingService;

    @PostMapping("/addReviewAndRating")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ReviewAndRating> addReviewAndRating(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
            return ResponseEntity.ok(reviewAndRatingService.addReview(userEmail ,reviewDTO)) ;
    }
    @PutMapping("/changeReviewAndRating")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ReviewAndRating> changeReviewAndRating(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(reviewAndRatingService.changeReview(userEmail,reviewDTO));
    }
    @PostMapping("/toggleLikeToReview")
    public ResponseEntity<ReviewAndRating> toggleLikeToReview(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(reviewAndRatingService.toggleLikeToReview(userEmail,reviewDTO));
    }
    @PostMapping("/toggleDisLikeToReview")
    public ResponseEntity<ReviewAndRating> toggleDisLikeToReview(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return ResponseEntity.ok(reviewAndRatingService.toggleDislikeToReview(userEmail, reviewDTO));
    }
}
