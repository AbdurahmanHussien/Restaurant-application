package com.spring.restaurant.repository;

import com.spring.restaurant.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByUserId(Long userId, Pageable pageable);


}
