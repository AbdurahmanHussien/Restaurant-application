package com.spring.restaurant.repository;
import com.spring.restaurant.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
