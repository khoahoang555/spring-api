package com.springboot.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagingHedgeDTO {
    private List<HedgeDTO> results;
    private long totalItems;
    private int totalPages;
    private int currentPage;
}
