package com.spring.restaurant.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AllSystemOrderDto implements Serializable {

    private Long id;
    private String userName;
    private String code;

    private long totalPrice;

    private long totalQuantity;

    private LocalDateTime createdAt;


    private List<OrderProductDTO> items;

}
