package com.example.samuraitravel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.service.ReviewService;


@Controller
public class ReviewController {
   
	private final ReviewRepository reviewRepository;
	private final ReviewService reviewService;
	private final HouseRepository houseRepository;
	
	@Autowired
	public ReviewController(ReviewRepository reviewRepository , ReviewService reviewService , HouseRepository houseRepository) {
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
		this.houseRepository = houseRepository;
	}
	
	
	@GetMapping("/houses/reviews")
	public String index(Model model) {
		List<Review> listReviews = reviewService.getAllReviews();
		
		model.addAttribute("listreviews" , listReviews);
		
		return "houses/reviews/index";  /*  requestMapping似合わせる。ここの情報がページとして表示される*/
	}
	
	@GetMapping("/houses/reviewsforall")
	public String index2(Model model) {
		List<Review> listReviews = reviewService.getAllReviews();
		
		model.addAttribute("listreviews" , listReviews);
		
		return "houses/reviews/indexforall";  /*  requestMapping似合わせる。ここの情報がページとして表示される*/
	}
	
	@GetMapping("/houses/reviews/create")
 	public String register(Model model) {
		Review review = new Review();
 		model.addAttribute("reviewRegsiterForm" , new ReviewRegisterForm());
 		model.addAttribute("review",review);
 		return "houses/reviews/create";
 	}
	

	@PostMapping("/houses/{houseid}/reviews/saveReview")
	public String saveReview(@ModelAttribute("review") Review review) {
		reviewService.saveReview(review);		
		return "redirect:/houses/{houseid}/reviews";
	}
	
    @PostMapping("/houses/showFormForUpdata_{id}")
    public String showFormForUpdate(@PathVariable(name = "id") Integer id, Model model) {
    	Review review = reviewService.getReviewById(id).orElse(null);
       
        model.addAttribute("Review", review);
     
        return "houses/reviews/edit";
    }   

  	@GetMapping("/houses/deleteReview/{id}")
      public String deleteReview(@PathVariable(value = "id") Integer id) {
          this.reviewService.deleteReview(id);
          // 削除が完了したら、ホームページに戻ります。
          return "redirect:/";
      }
  	
  	
  	//@GetMapping("reviews/reviewDetail/{houseid}")
  	//public String reviewDetail(@PathVariable(value = "id") Integer id , Model model) {
   	//	Review review = reviewService.getReviewById(id);
   		
   	//	model.addAttribute("review" , review);
  		
  	//	return "houses/reviews/detail";
  	//}
  	
  	
 	//@GetMapping("houses/reviews/{houseid}")
   	//public String getReviews(@PathVariable("houseid") House houseid, Model model) {
  	//List<Review> reviews = reviewRepository.findByHouseid(houseid);
  	//model.addAttribute("reviews",reviews);
  		
  	//	return "houses/reviews/show";  
 	//}
  	
     
}



