package com.example.samuraitravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.OkiniiriRepository;
import com.example.samuraitravel.repository.UserRepository;

@Controller
@RequestMapping
public class OkiniiriController {
	 private HouseRepository houseRepository ;
	 private UserRepository userRepository ;
	private OkiniiriRepository okiniiriRepsotiroy;    
     
	 
     public void HouseController(HouseRepository houseRepository ,UserRepository userRepository) {
         this.okiniiriRepsotiroy = okiniiriRepsotiroy;
    	 this.houseRepository = houseRepository;
         this.userRepository = userRepository;
     }     
   
}
