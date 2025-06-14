package com.spring.restaurant.repository;
import com.spring.restaurant.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {}
