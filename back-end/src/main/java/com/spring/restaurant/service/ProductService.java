package com.spring.restaurant.service;
import com.spring.restaurant.dto.ProductDto;
import com.spring.restaurant.entity.Product;
import com.spring.restaurant.exceptions.BadRequestException;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.mapper.ProductMapper;
import com.spring.restaurant.repository.CategoryRepository;
import com.spring.restaurant.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ProductService implements IProductService {


    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    public ProductService(ProductRepository productRepository , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    @CacheEvict(value = {"products", "productsByCategory"}, allEntries = true)
    public ProductDto addProduct(ProductDto productDto) throws Exception {
        if(Objects.nonNull(productDto.getId())){

            throw new BadRequestException("id.notempty");
        }

        return saveAndReturnDto(productDto);

    }

    @Override
    @CacheEvict(value = {"products", "productsByCategory"}, allEntries = true)
    public ProductDto updateProduct(ProductDto productDto) throws Exception {
        if(Objects.isNull(productDto.getId())){

            throw new BadRequestException("id.empty");
        }

        productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("product.notfound"));

        return saveAndReturnDto(productDto);
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product= productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product.notfound"));
        return toDto(product);
    }


    @Override
    @Cacheable("products")
    public Page<ProductDto> getAllProducts(int page, int size) {

        if(page <= 0){
            throw new BadRequestException("page.zero");
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id"));
        Page<Product> productPage = productRepository.findAllByOrderByIdAsc(pageable);
        if (productPage.isEmpty()) {
            throw new ResourceNotFoundException("page.notfound");
        }
        return productPage.map(this::toDto);

    }

    @Override
    @CacheEvict(value = {"products", "productsByCategory"}, allEntries = true)
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product.notfound"));
             productRepository.delete(product);
    }

    @Override
    @CacheEvict(value = {"products", "productsByCategory"}, allEntries = true)
    public void deleteProductByIds(List<Long> ids) {
        List<Product> products = productRepository.findAllById(ids);
        if (products.size() != ids.size()) {
            throw new BadRequestException("there is not enough products to delete");
        }

        productRepository.deleteAll(products);
    }

    @Override
    @Cacheable("productsByCategory")
    public Page<ProductDto> getAllByCategoryId(long id, int page, int size) {
        if(page <= 0){
            throw new BadRequestException("page.zero");
        }
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Product> productPage = productRepository.getAllByCategoryId(id, pageable);
        if (productPage.isEmpty()) {
            throw new ResourceNotFoundException("page.notfound");
        }
        return productPage.map(this::toDto);
    }

    @Override
    public List<ProductDto> addListOfProducts(List<ProductDto> productDtos) {

        List<Product> products = productDtos.stream()
                                .map(this::toEntity)
                                     .collect(Collectors.toList());

        List<Product> saved = productRepository.saveAll(products);
        return saved.stream()
                     .map(this::toDto)
                        .collect(Collectors.toList());
    }


    @Override
    public List<ProductDto> updateListOfProducts(List<ProductDto> productDtos) {

        productDtos.forEach(dto -> {
            if (dto.getId() == null) {
                throw new BadRequestException("id.empty");
            }
            productRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("product.notfound" + dto.getId()));

            Product product = toEntity(dto);
            product.setId(dto.getId());
            productRepository.save(product);
        });

        return productDtos;
    }

    @Override
     public Page<ProductDto> searchProducts(String keyword, int page, int size) {
        if(page <= 0){
            throw new BadRequestException("page.zero");
        }
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> productsPage = productRepository.searchByNameOrDescription(keyword, pageable);
        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("page.notfound");
        }
        return productsPage.map(this::toDto);
    }

    @Override
    public Page<ProductDto> searchProductsByCategory(Long categoryId, String keyword, int page, int size) {
        if(page <= 0){
            throw new BadRequestException("page.zero");
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> productsPage = productRepository.searchByCategoryIdAndNameOrDescription(categoryId, keyword, pageable);

        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("page.notfound");
        }
        return productsPage.map(this::toDto);
    }



    private ProductDto saveAndReturnDto(ProductDto dto) {
        Product product = toEntity(dto);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    private Product toEntity(ProductDto dto) {
        return ProductMapper.PRODUCT_MAPPER.productDtoToProduct(dto , categoryRepository);
    }

    private ProductDto toDto(Product product) {
        return ProductMapper.PRODUCT_MAPPER.productToProductDto(product);
    }

}
