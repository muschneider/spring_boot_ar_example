package com.example.demo.controllers;

import com.example.demo.entity.Comment;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

  @GetMapping
  public List<Comment> getComments() {
    return new Comment().findAll();
  }

  @PostMapping
  @Transactional
  public Comment createComment(@RequestBody Comment comment) {
    return comment.save();
  }

  @DeleteMapping("/{id}")
  public void deleteComment(@PathVariable Long id) {
    Comment comment = new Comment();
    comment.setId(id);
    comment.delete();
  }
}
