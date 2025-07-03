package com.spring.restaurant.controller;

import com.spring.restaurant.dto.CommentDto;
import com.spring.restaurant.service.impl.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    @Operation(summary = "add comment")
    public ResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok().body( commentService.addComment(commentDto));
    }

    @GetMapping
    @Operation(summary = "get all comments")
    public ResponseEntity<List<CommentDto>> getComments() {
        return ResponseEntity.ok().body( commentService.getComments());
    }

    @GetMapping("/{contactId}")
    @Operation(summary = "get comments by contact id")
    public ResponseEntity<List<CommentDto>> getCommentsByContactId( @PathVariable Long contactId) {
        return ResponseEntity.ok().body( commentService.getCommentsByContactId(contactId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete comment by id")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
