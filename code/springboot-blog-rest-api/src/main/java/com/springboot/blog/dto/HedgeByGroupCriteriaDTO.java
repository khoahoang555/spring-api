package com.springboot.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HedgeByGroupCriteriaDTO {
    @NotBlank(message = "売買 type is required!")
    private String buyOrSell;
    private String search;
    private boolean checkDone = false;
}
