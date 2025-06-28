package com.spring.restaurant.service;
import com.spring.restaurant.dto.CategoryDto;
import java.util.List;


public interface ICategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories(Long userId);
    CategoryDto getCategoryById(long id);
    CategoryDto updateCategory(CategoryDto categoryDto);
    void deleteCategory(long id);
    List<CategoryDto> addListOfCategory(List<CategoryDto> categoryDtos);
    List<CategoryDto> updateListOfCategory(List<CategoryDto> categoryDtos);
    void deleteCategoryByIds(List<Long> ids);



}
