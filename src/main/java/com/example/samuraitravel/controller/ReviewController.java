package com.example.samuraitravel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.repository.UserRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.ReviewService;


@Controller
public class ReviewController {
   
	private final ReviewRepository reviewRepository;
	private final ReviewService reviewService;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	
	
	@Autowired
	public ReviewController(ReviewRepository reviewRepository , ReviewService reviewService , HouseRepository houseRepository ,UserRepository userRepository) {
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
		
	}
	
	
	
	@GetMapping("/houses/reviews")
	public String index(Review review,Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		List<Review> listReviews = reviewService.getAllReviews();
		List<House> house = houseRepository.findAll();
		List<User> user = userRepository.findAll();
		User user2 = userDetailsImpl.getUser(); 
		model.addAttribute("listreviews" , listReviews);
		model.addAttribute("house",house);
		model.addAttribute("user",user);
		model.addAttribute("user2",user2);
		return "houses/reviews/index";  /*  requestMapping似合わせる。ここの情報がページとして表示される*/
	} 
	
		@GetMapping("/houses/reviews/{id}")
	public String edit(@PathVariable(name = "id") Integer id ,House houseid,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model) {
		Review review = reviewRepository.getReferenceById(id);
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(),review.getHouseid(),review.getUserid(),review.getCommenttext(),review.getValue());
		model.addAttribute("reviewEditForm" , reviewEditForm);
		
		return "houses/reviews/edit";
	}	
		
		
	@PostMapping("/houses/reviews/{id}/update")
	public String update(@PathVariable(name = "id") Integer  id,@ModelAttribute @Validated ReviewEditForm reviewEditForm,BindingResult bindingResult,RedirectAttributes redirectAttributes,Model model){
		
		if(bindingResult.hasErrors()) {
			return "/houses/reviews/edit";
		}
		reviewService.update(reviewEditForm);
		
		redirectAttributes.addFlashAttribute("successMessage","編集しました");
			return "redirect:/";

 }
	 @PostMapping("/houses/{id}/reviews/delete")
	 	public String delete(@PathVariable(name ="id")Integer id, RedirectAttributes redirectAttributes) {
	 		reviewRepository.deleteById(id);
	 		redirectAttributes.addFlashAttribute("successMessage","削除しました");
	 		return "redirect:/";
	 }
}
	//ここのIDは、レビューのID.Houseidを用いると、複数のデータが集まってしまう。
  	//@GetMapping("/houses/reviews/{id}/edit")
	//public String edit(@PathVariable(name = "id") Integer id,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model) {
  		//Review review = reviewRepository.getReferenceById(id);
	//	House house = houseRepository .getReferenceById(id);
	//	User user = userRepository.getReferenceById(id);
	//	List<Review> reviews = reviewRepository.findByUserid(user);
  		//List<Review> reviews = reviewRepository.findTop6ByHouseidOrderByUpdatedAtDesc(house);
  		
	//	model.addAttribute("review",reviews);
  		//model.addAttribute("house",house);
  		//ReviewEditForm reviewEditForm = new ReviewEditForm();
  		//model.addAttribute("reviewEdtiForm",reviewEditForm);
  		
  	//	return "houses/reviews/edit";
  	//}
	
  