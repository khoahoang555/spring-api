package com.example.springcodeapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HedgeGroupDTO {
    private UUID id;
    @Size(max = 64, message = "Name have max length 64 character!")
    private String name;
}
