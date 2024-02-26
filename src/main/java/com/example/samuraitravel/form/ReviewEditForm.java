package com.example.samuraitravel.form;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewEditForm {

	
	 Integer id;
	
	
	 House houseid;
	
	@NotNull
	  User userid;
	
	
	@NotBlank
	 String commenttext;
	
	
	Integer value;
}