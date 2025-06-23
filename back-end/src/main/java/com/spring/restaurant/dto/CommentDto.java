package com.spring.restaurant.dto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {

    private Long id;

    private String value;

    private long contactInfoId;

    private long userId;

    private String username;

    private LocalDateTime createdDate;


}
