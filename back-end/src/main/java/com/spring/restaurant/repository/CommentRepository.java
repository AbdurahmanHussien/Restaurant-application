package com.spring.restaurant.repository;

import com.spring.restaurant.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Query("SELECT DISTINCT c FROM Comment c LEFT JOIN FETCH c.user")
    List<Comment> findAllWithUser();

    @Query("SELECT DISTINCT c FROM Comment c LEFT JOIN FETCH c.user WHERE c.contactInfo.id = :contactInfoId ORDER BY c.createdDate asc ")
    List<Comment> findByContactInfoIdWithUser(@Param("contactInfoId") Long contactInfoId);
    
    List<Comment> findByContactInfoId(Long contactInfoId);
}
