package com.spring.restaurant.repository.auth;

import com.spring.restaurant.entity.auth.Role;
import com.spring.restaurant.utils.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleType(RoleType roleType);

}
