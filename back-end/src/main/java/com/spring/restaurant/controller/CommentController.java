package com.spring.restaurant.controller;
import com.spring.restaurant.dto.CommentDto;
import com.spring.restaurant.service.impl.CommentService;
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
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok().body( commentService.addComment(commentDto));
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments() {
        return ResponseEntity.ok().body( commentService.getComments());
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<List<CommentDto>> getCommentsByContactId( @PathVariable Long contactId) {
        return ResponseEntity.ok().body( commentService.getCommentsByContactId(contactId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
