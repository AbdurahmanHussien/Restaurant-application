package com.spring.restaurant.repository;

import com.spring.restaurant.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    Page<OrderItem> findAll(Pageable pageable);

    List<OrderItem> findAllByUserId(Long userId);


}
