package com.spring.restaurant.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {


    private Long id;
    @NotEmpty(message = "product.name.notblank")
    private  String name;

    @NotNull
    private String imagePath;

    @NotNull
    private String description;

    @Min(value = 20 , message = "product.price.min")
    private int price;

    private Long categoryId;
}
