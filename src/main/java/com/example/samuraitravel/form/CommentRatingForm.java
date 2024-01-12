package com.example.samuraitravel.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRatingForm {

    
    @NotBlank(message = "レビューを入力してください。")
    public String comment_text;   
      
    
    @NotNull(message = "点数を入力してください。")
    @Min(value = 1, message = "点数は1点以上に設定してください。")
    public Integer value;     

	}
