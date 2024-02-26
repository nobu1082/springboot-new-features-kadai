package com.example.samuraitravel.service;
 
 import java.util.List;

import org.springframework.stereotype.Service;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;

import jakarta.transaction.Transactional;
 
 @Service
 public class ReviewService{
     private final ReviewRepository reviewRepository;

     public ReviewService(ReviewRepository reviewRepository) {
    	 this.reviewRepository = reviewRepository;
    }
     @Transactional
     public void update(ReviewEditForm reviewEditForm) {
    	 Review review = reviewRepository.getReferenceById(reviewEditForm.getId()); 	 
     
     	review.setId(reviewEditForm.getId());
     	review.setHouseid(reviewEditForm.getHouseid());
     	review.setUserid(reviewEditForm.getUserid());
        review.setCommenttext(reviewEditForm.getCommenttext());
        review.setValue(reviewEditForm.getValue());
        
       
        reviewRepository.save(review);
  }
     @Transactional
     public void create(ReviewRegisterForm reviewRegisterForm ,User user,House house) {
    	 
    	 Review review = new Review();
    	review.setUserid(user);
    	review.setHouseid(house);
        review.setCommenttext(reviewRegisterForm.getCommenttext());
     	review.setValue(reviewRegisterForm.getValue());
         
         reviewRepository.save(review);
     }  
     
     
	//@Transactional
	//public Review saveReview(Review review) {
	//	return reviewRepository.save(review);
	//}
	
	@Transactional
	public List<Review> getAllReviews(){
		return reviewRepository.findAll();
	}
	
	//@Transactional
	//public Optional<Review> getReviewById(Integer id){
	//	return reviewRepository.findById(id);
	//}
	
	//@Transactional
	//public Object deleteReview(Integer id) {
	//	return reviewRepository.deleteById(id);
	//}
	
	
	
}
