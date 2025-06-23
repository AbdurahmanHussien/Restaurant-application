package com.spring.restaurant.repository;

import com.spring.restaurant.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {

   @Query("SELECT c FROM ContactInfo c join User u on c.user.id = u.id where u.id = :userId")
   List<ContactInfo> findByUserId(Long userId);
}
