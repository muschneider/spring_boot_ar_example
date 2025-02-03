package com.example.demo.controllers;

import com.example.demo.entity.Post;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

  @GetMapping
  public List<Post> getPosts() {
    return new Post().findAll();
  }

  @PostMapping
  @Transactional
  public Post createPost(@RequestBody Post post) {
    return post.save();
  }

  @DeleteMapping("/{id}")
  public void deletePost(@PathVariable Long id) {
    Post post = new Post();
    post.setId(id);
    post.delete();
  }
}
