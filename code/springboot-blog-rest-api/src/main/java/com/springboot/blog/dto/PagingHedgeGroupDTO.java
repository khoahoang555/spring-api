package com.springboot.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingHedgeGroupDTO {
    private List<HedgeGroupDTO> results;
    private long totalItems;
    private int totalPages;
    private int currentPage;
}
