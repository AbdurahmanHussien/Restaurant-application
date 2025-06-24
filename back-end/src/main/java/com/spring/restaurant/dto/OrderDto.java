package com.spring.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderDto implements Serializable {


    private Long id;
    private String code;


    private long totalPrice;

    private long totalQuantity;

    private LocalDateTime createdAt;



    private List<OrderProductDTO> items;


    @NotBlank(message="user.notblank")
    private long userId;
}
