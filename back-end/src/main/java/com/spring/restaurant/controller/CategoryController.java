package com.spring.restaurant.controller;
import com.spring.restaurant.dto.CategoryDto;
import com.spring.restaurant.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    @Operation(summary = "Add category")
    public ResponseEntity<CategoryDto> addCategory( @Valid @RequestBody CategoryDto categoryDto) throws Exception {
        CategoryDto saved = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/list")
    @Operation(summary = "Add categories")
    public ResponseEntity<List<CategoryDto>> addCategories( @Valid @RequestBody List<CategoryDto> categoryDtoList) throws Exception {

        return ResponseEntity.ok(categoryService.addListOfCategory(categoryDtoList));
    }


    @PutMapping
    @Operation(summary = "Update category")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) throws Exception {
        CategoryDto updated = categoryService.updateCategory(categoryDto);
        return ResponseEntity.ok(updated);
    }


    @PutMapping("/list")
    @Operation(summary = "Update categories")
    public ResponseEntity<List<CategoryDto>> updateCategories(@Valid @RequestBody List<CategoryDto> categoryDtoList) throws Exception {
        return ResponseEntity.ok(categoryService.updateListOfCategory(categoryDtoList));
    }
    @GetMapping
    @Operation(summary = "Get All category")
    public ResponseEntity<List<CategoryDto>> getAllProducts() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category By Id")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long id) throws Exception {
        CategoryDto category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category by Id")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all categories by Ids")
    public ResponseEntity<Void> deleteCategoriesByIds(@RequestParam List<Long> ids) {
        categoryService.deleteCategoryByIds(ids);
        return ResponseEntity.noContent().build();
    }

}
