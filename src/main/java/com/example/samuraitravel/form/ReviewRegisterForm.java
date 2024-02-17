package com.example.samuraitravel.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	@NotBlank
	private String name;
	
	@NotBlank
	private String commenttext;
	
	@NotBlank
	private Integer value;
}
