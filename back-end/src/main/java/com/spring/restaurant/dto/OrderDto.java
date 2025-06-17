package com.spring.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderDto {


    private Long id;
    private String code;


    private long totalPrice;

    private long totalQuantity;

    private LocalDateTime createdAt;



    private List<OrderProductDTO> items;


    @NotBlank(message="user.notblank")
    private long userId;
}
