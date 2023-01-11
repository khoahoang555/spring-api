package com.springboot.blog.dto;

import com.springboot.blog.constants.Choices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HedgeOrderCriteriaDTO {
    private Integer page = 1;
    private Integer size = 1000;
    private String search;
    private String fromDate;
    private String toDate;
    private Choices.HEDGE_ORDER_STATUS status = Choices.HEDGE_ORDER_STATUS.RUNNING;
    private String group;
}
