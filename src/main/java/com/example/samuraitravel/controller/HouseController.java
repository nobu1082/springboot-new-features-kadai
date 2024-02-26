package com.example.samuraitravel.controller;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReservationInputForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.repository.UserRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.ReviewService;
 
 @Controller
 @RequestMapping
public class HouseController {
	
     private final HouseRepository houseRepository;
	 private final ReviewRepository reviewRepository;    
     private final ReviewService reviewService;
	 private final UserRepository userRepository;
	
	 
     public HouseController(HouseRepository houseRepository ,ReviewRepository reviewRepository,ReviewService reviewService,UserRepository userRepository) {
         this.houseRepository = houseRepository;
         this.reviewRepository = reviewRepository;
         this.reviewService = reviewService;
         this.userRepository = userRepository;
       
     }     
   
     @GetMapping("/houses")
     public String index(@RequestParam(name = "keyword", required = false) String keyword,
                         @RequestParam(name = "area", required = false) String area,
                         @RequestParam(name = "price", required = false) Integer price,  
                         @RequestParam(name = "order", required = false) String order,
                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                         Model model) 
     {
         Page<House> housePage;
                 
         if (keyword != null && !keyword.isEmpty()) {
        	 if (order != null && order.equals("priceAsc")) {
                 housePage = houseRepository.findByNameLikeOrAddressLikeOrderByPriceAsc("%" + keyword + "%", "%" + keyword + "%", pageable);
             } else {
                 housePage = houseRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%", "%" + keyword + "%", pageable);
             }     
         } else if (area != null && !area.isEmpty()) {
        	 if (order != null && order.equals("priceAsc")) {
                 housePage = houseRepository.findByAddressLikeOrderByPriceAsc("%" + area + "%", pageable);
             } else {
                 housePage = houseRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
             }   
         } else if (price != null) {
        	 if (order != null && order.equals("priceAsc")) {
                 housePage = houseRepository.findByPriceLessThanEqualOrderByPriceAsc(price, pageable);
             } else {
                 housePage = houseRepository.findByPriceLessThanEqualOrderByCreatedAtDesc(price, pageable);
             }      
         } else {
        	 if (order != null && order.equals("priceAsc")) {
                 housePage = houseRepository.findAllByOrderByPriceAsc(pageable);
             } else {
                 housePage = houseRepository.findAllByOrderByCreatedAtDesc(pageable);   
             }    
         }                
         
         model.addAttribute("housePage", housePage);
         model.addAttribute("keyword", keyword);
         model.addAttribute("area", area);
         model.addAttribute("price", price); 
         model.addAttribute("order", order);
         
         return "houses/index";
     }
     
     @GetMapping("/houses/{id}")
     public String show(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model) {
         House house = houseRepository.getReferenceById(id);
         //List<Review> reviews = reviewRepository.findByHouseid(house);
         List<Review> reviews = reviewRepository.findTop6ByHouseidOrderByUpdatedAtDesc(house);
         model.addAttribute("reviews",reviews);
         model.addAttribute("house", house);   
         model.addAttribute("reservationInputForm", new ReservationInputForm());
 
         return "houses/show";
     }
     
     @GetMapping("/houses/{id}/reviews")
     public String showall(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model) {
    	 House house2 = houseRepository.getReferenceById(id);
    	List<Review> reviews2 = reviewRepository.findByHouseid(house2);
    	 User user2 = userDetailsImpl.getUser();
    	 model.addAttribute("user" , user2);
        
    	 model.addAttribute("reviews2", reviews2);
         model.addAttribute("house2",house2);
         return "houses/reviews/show";
     }
     
     @GetMapping("/houses/{id}/reviews/create")
 	public String register(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,Model model) {
 		model.addAttribute("reviewRegisterForm",new ReviewRegisterForm());
 		return "/houses/reviews/register";
 	}
     
     @PostMapping("/houses/{id}/reviews/create")
     public String create(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
         if (bindingResult.hasErrors()) {
             return "/houses/index";
         }
         
        
         User user = userDetailsImpl.getUser();
         House house = houseRepository.getReferenceById(id);
         reviewService.create(reviewRegisterForm,user,house);
         
         redirectAttributes.addFlashAttribute("successMessage", "レビューを登録しました。");
         return "redirect:/houses";
     }
     
}
