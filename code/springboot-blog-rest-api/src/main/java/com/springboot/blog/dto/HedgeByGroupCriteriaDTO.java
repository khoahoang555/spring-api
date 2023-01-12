package com.springboot.blog.dto;

import com.springboot.blog.constants.Choices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HedgeByGroupCriteriaDTO {
    private Choices.BILLING_SELLING buyOrSell = Choices.BILLING_SELLING.FOREIGN_CURRENCY_RECEIPT;
    private String search;
    private List<String> status = Arrays.asList(
            Choices.HEDGE_ORDER_STATUS.NOT_RUN.getKey(),
            Choices.HEDGE_ORDER_STATUS.PLANNING.getKey(),
            Choices.HEDGE_ORDER_STATUS.RUNNING.getKey()
    );
}
