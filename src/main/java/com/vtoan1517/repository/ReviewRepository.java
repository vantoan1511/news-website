package com.vtoan1517.repository;

import com.vtoan1517.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByArticleId(long id, Pageable pageable);
    Page<Review> findAllByUserId(long id, Pageable pageable);

}
