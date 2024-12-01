package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;

public interface ReviewAndRatingService {
     ReviewAndRating addReview(String userEmail,ReviewDTO reviewDTO);

     ReviewAndRating changeReview(String userEmail,ReviewDTO reviewDTO);

    ReviewAndRating toggleLikeToReview(String userEmail,ReviewDTO reviewDTO);

    ReviewAndRating toggleDislikeToReview(String userEmail, ReviewDTO reviewDTO);
}
