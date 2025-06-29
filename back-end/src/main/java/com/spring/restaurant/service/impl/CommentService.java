package com.spring.restaurant.service.impl;

import com.spring.restaurant.dto.CommentDto;
import com.spring.restaurant.entity.Comment;
import com.spring.restaurant.entity.auth.User;
import com.spring.restaurant.mapper.CommentMapper;
import com.spring.restaurant.repository.CommentRepository;
import com.spring.restaurant.service.ICommentService;
import com.spring.restaurant.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final AuthenticationService userService;


    @Override
    @Caching(evict = {
            @CacheEvict(value = "comments", key = "'all'"),
            @CacheEvict(value = "comments", key = "#commentDto.contactInfoId"),
            @CacheEvict(value = "contacts" , allEntries = true)
    })
    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        User user = userService.getUserById(commentDto.getUserId());
        comment.setUser(user);
       Comment saved = commentRepository.save(comment);
       CommentDto savedCommentDto = CommentMapper.INSTANCE.toDto(saved);
       savedCommentDto.setUserId(user.getId());
       savedCommentDto.setUsername(user.getUsername());
       return savedCommentDto ;
    }

    @Override
    @Cacheable(value = "comments", key = "'all'")
    public List<CommentDto> getComments() {
        List<Comment> comments = commentRepository.findAllWithUser();
        return comments.stream()
                .map(comment -> {
                    CommentDto dto = CommentMapper.INSTANCE.toDto(comment);
                    if (comment.getUser() != null) {
                        dto.setUsername(comment.getUser().getUsername());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "comments", key = "#contactId")
    public List<CommentDto> getCommentsByContactId(Long contactId) {
        List<Comment> comments = commentRepository.findByContactInfoIdWithUser(contactId);
        return comments.stream()
                .map(comment -> {
                    CommentDto dto = CommentMapper.INSTANCE.toDto(comment);
                    if (comment.getUser() != null) {
                        dto.setUsername(comment.getUser().getUsername());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "comments", allEntries = true),
            @CacheEvict(value = "contacts" , allEntries = true)

    })
    @CacheEvict(value = "comments", allEntries = true)
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }
}
