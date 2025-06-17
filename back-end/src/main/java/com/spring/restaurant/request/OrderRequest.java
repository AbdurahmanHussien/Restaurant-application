package com.spring.restaurant.request;

import com.spring.restaurant.dto.OrderProductDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    @NotNull(message = "order.products.notblank")
    private List<OrderProductDTO> products;

    @Min(value = 1 , message = "order.price.min")
    private long totalPrice;

    @Min(value = 1 , message = "order.quantity.min")
    private long totalQuantity;

    private Long userId;


}
