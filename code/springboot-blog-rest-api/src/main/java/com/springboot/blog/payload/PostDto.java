package com.springboot.blog.payload;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private LocalDate startDate;
}
