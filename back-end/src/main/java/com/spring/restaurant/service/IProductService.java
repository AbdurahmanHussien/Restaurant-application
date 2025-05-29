package com.spring.restaurant.service;
import com.spring.restaurant.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IProductService {

    ProductDto addProduct(ProductDto productDto) throws Exception;
    ProductDto updateProduct(ProductDto productDto) throws Exception;
    ProductDto getProductById(long id);
    Page<ProductDto> getAllProducts(int page , int size);
    void deleteProduct(long id);
    void deleteProductByIds(List<Long> ids);
    Page<ProductDto> getAllByCategoryId(long id, int page , int size);
    List<ProductDto> addListOfProducts(List<ProductDto> productDtos);
    List<ProductDto> updateListOfProducts(List<ProductDto> productDtos);
    Page<ProductDto> searchProducts(String keyword , Pageable pageable );
    Page<ProductDto> searchProductsByCategory(Long categoryId, String keyword,  int page , int size);
}

