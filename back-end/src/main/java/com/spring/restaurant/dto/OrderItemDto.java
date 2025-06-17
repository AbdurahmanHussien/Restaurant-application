package com.spring.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


@Data
public class OrderItemDto {


    private Long id;
    private String code;


    private long totalPrice;

    private long totalQuantity;

    List<String> productsNames;

    @NotBlank(message="user.notblank")
    private long userId;
}
