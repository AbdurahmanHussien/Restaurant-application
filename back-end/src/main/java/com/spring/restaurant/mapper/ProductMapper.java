package com.spring.restaurant.mapper;
import com.spring.restaurant.dto.ProductDto;
import com.spring.restaurant.entity.Category;
import com.spring.restaurant.entity.Product;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.repository.CategoryRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);


    @Mapping(target = "category", expression = "java(getCategory(dto.getCategoryId(), categoryRepository))")
    Product productDtoToProduct(ProductDto dto ,@Context CategoryRepository categoryRepository );

    @Mapping(target = "categoryId", source = "category.id")
    ProductDto productToProductDto(Product product);

    List<ProductDto> toDtoList(List<Product> products);

    List<Product> DtosToProducts(List<ProductDto> productDtos);


    default Category getCategory(Long id, @Context CategoryRepository categoryRepository) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category.notfound"));
    }

}
