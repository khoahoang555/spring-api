package com.springboot.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HedgeByGroupCriteriaDTO {
    private String buyOrSell;
    private String search;
    private boolean checkDone = false;
}
