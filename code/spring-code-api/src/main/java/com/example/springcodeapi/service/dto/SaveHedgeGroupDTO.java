package com.example.springcodeapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveHedgeGroupDTO {
    @NotNull(message = "Name is not null!")
    @Size(max = 64, message = "Name have max length 64 characters!")
    private String name;
}
