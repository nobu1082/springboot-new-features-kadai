package com.example.samuraitravel.repository;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.samuraitravel.entity.Review;

@Repository
 public interface ReviewRepository extends JpaRepository<Review, Integer> {

}