package com.spring.restaurant.service.impl;
import com.spring.restaurant.dto.CategoryDto;
import com.spring.restaurant.entity.Category;
import com.spring.restaurant.exceptions.BadRequestException;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.mapper.CategoryMapper;
import com.spring.restaurant.repository.CategoryRepository;
import com.spring.restaurant.service.ICategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if(Objects.nonNull(categoryDto.getId())){

            throw new BadRequestException("id.notempty");
        }
        if(categoryRepository.findByName(categoryDto.getName())!=null){

            throw new BadRequestException("category.exists");
        }

        return saveAndReturnDto(categoryDto);
    }

    @Override
    public List<CategoryDto> getAllCategories(Long userId) {

        List <CategoryDto> categories = categoryRepository.findAll(Sort.by("name"))
                .stream()
                .map(this::toDto)
                .toList();

        List<Object[]> result = categoryRepository.findTopCategoriesByUser(userId);
        Long topCategoryId = result.stream()
                .findFirst()
                .map(r -> (Long) r[0])
                .orElse(null);

        List<CategoryDto> dtos = categories.stream()
                .map(category -> {
                    category.setRecommended(category.getId().equals(topCategoryId));
                    return category;
                })
                .toList();
            return dtos;


    }

    @Override
    @Cacheable(value = "category", key = "#id")
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category.notfound"));

        return toDto(category);
    }

    @Override
    @CacheEvict(value = {"categories", "category" }, allEntries = true)
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        if(Objects.isNull(categoryDto.getId())){

            throw new IllegalArgumentException("id.empty");
        }

        categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("category.notfound"));

        return saveAndReturnDto(categoryDto);
    }

    @Override
    @CacheEvict(value = {"categories", "category" }, allEntries = true)
    public void deleteCategory(long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category.notfound"));
        categoryRepository.delete(category);

    }

    @Override
    @CacheEvict(value = {"categories", "category" }, allEntries = true)
    public List<CategoryDto> addListOfCategory(List<CategoryDto> categoryDtos) {
        List<Category> categories = categoryDtos.stream()
                                .map(this::toEntity)
                                 .collect(Collectors.toList());

        List<Category> saved = categoryRepository.saveAll(categories);
        return saved.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value ={"categories", "category" }, allEntries = true)
    public List<CategoryDto> updateListOfCategory(List<CategoryDto> categoryDtos) {
        categoryDtos.forEach(dto -> {
            if (dto.getId() == null) {
                throw new BadRequestException("id.empty");
            }

            categoryRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("category.notfound"));

            Category category = toEntity(dto);
            category.setId(dto.getId());
            categoryRepository.save(category);
        });

        return categoryDtos;
    }

    @Override
    @CacheEvict(value = {"categories", "category" }, allEntries = true)
    public void deleteCategoryByIds(List<Long> ids) {
        List<Category> categories = categoryRepository.findAllById(ids);
        if (categories.size() != ids.size()) {
            throw new ResourceNotFoundException("category.notfound");
        }

        categoryRepository.deleteAll(categories);
    }

    private CategoryDto saveAndReturnDto(CategoryDto dto) {
        Category category = toEntity(dto);
        Category saved = categoryRepository.save(category);
        return toDto(saved);
    }

    private Category toEntity(CategoryDto dto) {
        return CategoryMapper.CATEGORY_MAPPER.categoryDTOToCategory(dto);
    }

    private CategoryDto toDto(Category category) {
        return CategoryMapper.CATEGORY_MAPPER.categoryToCategoryDTO(category);
    }
}
