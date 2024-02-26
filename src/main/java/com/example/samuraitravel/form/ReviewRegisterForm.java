package com.example.samuraitravel.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReviewRegisterForm {

   

    @NotBlank
    private String commenttext;

    @NotNull
    private Integer value;

    public ReviewRegisterForm(String commenttext, Integer value) {
        
        this.commenttext = commenttext;
        this.value = value;
    }
}
