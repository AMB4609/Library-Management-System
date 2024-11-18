package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;
import com.lambdacode.librarymanagementsystem.service.ReviewAndRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviewAndRating")
public class ReviewAndRatingController {
    @Autowired
    private ReviewAndRatingService reviewAndRatingService;

    @PostMapping("/addReviewAndRating")
    public ResponseEntity<ReviewAndRating> addReviewAndRating(@RequestBody ReviewDTO reviewDTO) {
            return ResponseEntity.ok(reviewAndRatingService.addReview(reviewDTO)) ;
    }
    @PostMapping("/changeReviewAndRating")
    public ResponseEntity<ReviewAndRating> changeReviewAndRating(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewAndRatingService.changeReview(reviewDTO));
    }
}
