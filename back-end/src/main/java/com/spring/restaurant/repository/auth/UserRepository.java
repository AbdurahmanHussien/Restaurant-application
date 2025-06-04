package com.spring.restaurant.repository.auth;

import com.spring.restaurant.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUserDetailsEmail(String email);


    Optional<User> findByUsername(String username);
}
