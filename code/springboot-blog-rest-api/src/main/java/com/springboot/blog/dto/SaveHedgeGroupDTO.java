package com.springboot.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveHedgeGroupDTO {
    @NotBlank(message = "グループ名は必ず指定してください。")
    @Size(max = 64, message = "グループ名は64文字以下に入力してください。")
    private String name;
}
