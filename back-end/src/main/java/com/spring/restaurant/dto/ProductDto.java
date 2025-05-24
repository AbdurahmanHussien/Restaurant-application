package com.spring.restaurant.dto;
import com.spring.restaurant.entity.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


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
