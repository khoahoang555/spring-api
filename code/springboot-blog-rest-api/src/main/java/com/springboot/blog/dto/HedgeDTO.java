package com.springboot.blog.dto;

import com.smart.exchange.orderservice.constant.Choices;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class HedgeDTO {
    private UUID id;
    private UUID hedgeGroupId;
    private String currencyPair;
    private String buyingAndSelling;
    private Long transactionAmount;
    private int percentage;
    private long amount;
    private String hedgeOrderName;
    private Choices.HEDGE_ORDER_STATUS status;
    private String period;
    private LocalDate startDate;
    private LocalDate endDate;
}
