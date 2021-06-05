package com.mariworld.mreview.repository;

import com.mariworld.mreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
