package com.example.springcodeapi.service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveHedgeGroupDTO {
    @NotNull(message = "Name is not null!")
    @Size(max = 64, message = "Name have max length 64 characters!")
    private String name;
}
