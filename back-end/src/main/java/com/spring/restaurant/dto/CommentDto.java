package com.spring.restaurant.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto implements Serializable {

    private Long id;

    private String value;

    private long contactInfoId;

    private long userId;

    private String username;

    private LocalDateTime createdDate;


}
