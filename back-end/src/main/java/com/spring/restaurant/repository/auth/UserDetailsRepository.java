package com.spring.restaurant.repository.auth;

import com.spring.restaurant.entity.auth.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository  extends JpaRepository<UserDetails, Long> {
}
