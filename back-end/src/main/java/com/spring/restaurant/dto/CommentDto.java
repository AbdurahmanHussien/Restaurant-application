package com.spring.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto implements Serializable {

    private Long id;

    @Size(min = 2 , message = "short.comment")
    @NotBlank
    private String value;

    private long contactInfoId;

    private long userId;

    private String username;

    private LocalDateTime createdDate;


}
