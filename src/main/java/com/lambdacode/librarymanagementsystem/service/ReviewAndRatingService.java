package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.dto.ReviewDTO;
import com.lambdacode.librarymanagementsystem.model.ReviewAndRating;

public interface ReviewAndRatingService {
     ReviewAndRating addReview(ReviewDTO reviewDTO);

     ReviewAndRating changeReview(ReviewDTO reviewDTO);
}
