package com.example.samuraitravel.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;

@Repository
 public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	public List<Review> findTop6ByHouseidOrderByUpdatedAtDesc(House houseid);

	public List<Review> findByHouseid(House houseid);
	
	
}