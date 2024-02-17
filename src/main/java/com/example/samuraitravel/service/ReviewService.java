package com.example.samuraitravel.service;
 
 import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;

import jakarta.transaction.Transactional;
 
 @Service
 public class ReviewService {
     private final ReviewRepository reviewRepository;    
     
     @Autowired
     public ReviewService(ReviewRepository reviewRepository) {
         this.reviewRepository = reviewRepository;        
     }
     @Transactional
     public void create(ReviewRegisterForm reviewRegisterForm) {
    	 Review review = new Review();
    	 
    	 review.setName(reviewRegisterForm.getName());
    	 review.setCommenttext(reviewRegisterForm.getCommenttext());
    	 review.setValue(reviewRegisterForm.getValue());
    	 
    	 reviewRepository.save(review);
     }
     
     
	@Transactional
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}
	
	@Transactional
	public List<Review> getAllReviews(){
		return reviewRepository.findAll();
	}
	
	@Transactional
	public Optional<Review> getReviewById(Integer id){
		return reviewRepository.findById(id);
	}
	
	@Transactional
	public void deleteReview(Integer id) {
		reviewRepository.deleteById(id);
	}
	
	
	
}
