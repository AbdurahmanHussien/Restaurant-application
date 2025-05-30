package com.spring.restaurant.mapper;
import com.spring.restaurant.dto.CategoryDto;
import com.spring.restaurant.entity.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ProductMapper.class)
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);


    CategoryDto categoryToCategoryDTO(Category category);


    Category categoryDTOToCategory(CategoryDto dto);

}
