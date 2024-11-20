package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;

import java.util.List;

public interface ReviewAndRatingService {
     ReviewAndRating addReview(String userEmail,ReviewDTO reviewDTO);

     ReviewAndRating changeReview(String userEmail,ReviewDTO reviewDTO);

    ReviewAndRating addLikeToReview(String userEmail,ReviewDTO reviewDTO);
}
