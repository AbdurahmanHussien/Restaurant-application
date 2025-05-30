package com.spring.restaurant.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {


    private List<Long> productIds;

    private long totalPrice;

    private long totalQuantity;


}
