package com.springboot.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HedgeGroupDTO {
    private UUID id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HedgeDTO> hedges;

}
