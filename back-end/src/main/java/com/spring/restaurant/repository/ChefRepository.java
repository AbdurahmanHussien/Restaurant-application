package com.spring.restaurant.repository;
import com.spring.restaurant.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChefRepository extends JpaRepository<Chef, Long> {}
