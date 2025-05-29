package com.spring.restaurant.repository;

import com.spring.restaurant.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



    Page<Product> getAllByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> searchByNameOrDescription(@Param("keyword") String keyword, Pageable pageable);

    @Query("""
          SELECT p FROM Product p 
          WHERE p.category.id = :categoryId 
           AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) 
                OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))
           """)
    Page<Product> searchByCategoryIdAndNameOrDescription(Long categoryId, String keyword, Pageable pageable);

    Page<Product> findAllByOrderByIdAsc(Pageable pageable);

}
