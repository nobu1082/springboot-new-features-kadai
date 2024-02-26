package com.example.samuraitravel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.FavoriteRepository;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.repository.UserRepository;
import com.example.samuraitravel.security.UserDetailsImpl;

@Controller

public class FavoriteController {
	 @Autowired
	 private HouseRepository houseRepository ;
	 private UserRepository userRepository ;
     private ReviewRepository reviewRepository;
	 private FavoriteRepository favoriteRepository;
     
     public FavoriteController(HouseRepository houseRepository ,UserRepository userRepository,ReviewRepository reviewRepository , FavoriteRepository favoriteRepository) {
    	 this.houseRepository = houseRepository;
         this.userRepository = userRepository;
         this.reviewRepository = reviewRepository;
         this.favoriteRepository =favoriteRepository;
     }     
     @GetMapping("/houses/{id}/favorites/show")
     public String showFavorite(@PathVariable(name = "id") Integer id,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
    	 
    	 //User user = new User();
    	 //user.setId(userId);
    	 User user = userRepository.getReferenceById(id);
    	 List<Favorite> favorites = favoriteRepository.findByUserid(user);
    	 
    	 List<House> houses = new ArrayList<>();
    	 for (Favorite favorite : favorites) {
    		 houses.add(favorite.getHouseid());
    	 }
    	
    	 model.addAttribute("favorites",favorites);
    	 model.addAttribute("userName",user.getName());
    		 	 
    	// User user = userDetailsImpl.getUser();         
       // User favoriteuser  = userRepository.getReferenceById(id);
         	
         //model.addAttribute("user" , user);
         return "/houses/favorites/show";
 }
   
}
