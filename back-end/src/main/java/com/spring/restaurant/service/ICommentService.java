package com.spring.restaurant.service;

import com.spring.restaurant.dto.CommentDto;

import java.util.List;

public interface ICommentService {

    CommentDto addComment(CommentDto commentDto);
    List<CommentDto> getComments();
    List<CommentDto> getCommentsByContactId(Long contactId);
    void deleteComment(Long id);
}
