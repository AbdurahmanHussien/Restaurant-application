package com.spring.restaurant.repository;

import com.spring.restaurant.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

   Category findByName(String name);

   @Query("SELECT p.category.id, SUM(oi.quantity) " +
           "FROM OrderItem oi " +
           "JOIN oi.order o " +
           "JOIN oi.product p " +
           "WHERE o.user.id = :userId " +
           "GROUP BY p.category.id " +
           "ORDER BY SUM(oi.quantity) DESC")
   List<Object[]> findTopCategoriesByUser(@Param("userId") Long userId);

}
