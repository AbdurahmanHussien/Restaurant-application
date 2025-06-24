package com.spring.restaurant.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDTO implements Serializable {

        @NotNull(message = "order.product.id.notblank")
        private Long productId;

        private String productName;

        @Min(value = 1, message = "order.product.quantity.min")
        private int quantity;

        @Min(value = 0, message = "order.product.price.min")
        private long price;
    }

