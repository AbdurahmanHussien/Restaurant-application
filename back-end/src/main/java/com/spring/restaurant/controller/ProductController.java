package com.spring.restaurant.controller;
import com.spring.restaurant.dto.ProductDto;
import com.spring.restaurant.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/api/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    @Operation(summary = "Add product")
    public ResponseEntity<ProductDto> addProduct( @Valid @RequestBody ProductDto productDto) throws Exception {
        ProductDto saved = productService.addProduct(productDto);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/list")
    @Operation(summary = "Add products")
    public ResponseEntity<List<ProductDto>> addProducts( @Valid @RequestBody List<ProductDto> productDtoList) throws Exception {

        return ResponseEntity.ok(productService.addListOfProducts(productDtoList));
    }

    @PutMapping
    @Operation(summary = "Update product")
    public ResponseEntity<ProductDto> updateProduct( @Valid @RequestBody ProductDto productDto) throws Exception {
        ProductDto updated = productService.updateProduct(productDto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/list")
    @Operation(summary = "Update products")
    public ResponseEntity<List<ProductDto>> updateProducts( @Valid @RequestBody List<ProductDto> productDtoList) throws Exception {
        return ResponseEntity.ok(productService.updateListOfProducts(productDtoList));
    }

    @GetMapping
    @Operation(summary = "Get All products")
    public Page<ProductDto> getAllProducts( @RequestParam int page , int size) {
       return productService.getAllProducts(page, size);

    }

    @GetMapping("byCatId")
    @Operation(summary = "Get All products By Category Id")
    public Page<ProductDto> getAllByCategoryId(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam int page,
            @RequestParam int size) {
        return productService.getAllByCategoryId(categoryId, page ,  size);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get product By Id")
    public ResponseEntity<ProductDto> getProductById(  @PathVariable int id) throws Exception {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by Id")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete all product by Ids")
    public ResponseEntity<Void> deleteProductsByIds(@RequestParam List<Long> ids) {
        productService.deleteProductByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search Method")
    public Page<ProductDto> searchProducts(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page-1, size);
        return productService.searchProducts(keyword, pageable);

    }

    @GetMapping("/searchByCatId")
    @Operation(summary = "Search Method")
    public Page<ProductDto> searchProductsByCategory(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);
        return productService.searchProductsByCategory(categoryId, keyword, page, size);

    }
}
