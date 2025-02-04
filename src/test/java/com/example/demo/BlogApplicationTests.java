package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BlogApplicationTests {

  @Autowired private MockMvc mockMvc;

  private User user;
  private Post post;
  private Comment comment;

  @BeforeEach
  void setup() {
    user = new User();
    user.setUsername("testuser");
    user.setEmail("testuser@example.com");
    user.save();

    post = new Post();
    post.setTitle("Test Post");
    post.setContent("This is a test post.");
    post.setUser(user);
    post.save();

    comment = new Comment();
    comment.setText("Test Comment");
    comment.setPost(post);
    comment.save();
  }

  @Test
  void testUserCreation() {
    User foundUser = User.findById(User.class, user.getId());
    assertNotNull(foundUser);
    assertEquals("testuser", foundUser.getUsername());
  }

  @Test
  void testFindAllUsers() {
    List<User> users = User.findAll(User.class);
    assertFalse(users.isEmpty());
  }

  @Test
  void testPostCreation() {
    Post foundPost = Post.findById(Post.class, post.getId());
    assertNotNull(foundPost);
    assertEquals("Test Post", foundPost.getTitle());
  }

  @Test
  void testFindAllPosts() {
    List<Post> posts = Post.findAll(Post.class);
    assertFalse(posts.isEmpty());
  }

  @Test
  void testCommentCreation() {
    Comment foundComment = Comment.findById(Comment.class, comment.getId());
    assertNotNull(foundComment);
    assertEquals("Test Comment", foundComment.getText());
  }

  @Test
  void testFindAllComments() {
    List<Comment> comments = Comment.findAll(Comment.class);
    assertFalse(comments.isEmpty());
  }

  @Test
  void testUserController() throws Exception {
    mockMvc.perform(get("/users")).andExpect(status().isOk());
  }

  @Test
  void testPostController() throws Exception {
    mockMvc.perform(get("/posts")).andExpect(status().isOk());
  }

  @Test
  void testCommentController() throws Exception {
    mockMvc.perform(get("/comments")).andExpect(status().isOk());
  }

  @Test
  void testCreateUser() throws Exception {
    mockMvc
        .perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"newuser\",\"email\":\"newuser@example.com\"}"))
        .andExpect(status().isOk());
  }

  @Test
  void testCreatePost() throws Exception {
    mockMvc
        .perform(
            post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"title\":\"New Post\",\"content\":\"Post Content\", \"user\": {\"id\": "
                        + user.getId()
                        + "}}"))
        .andExpect(status().isOk());
  }

  @Test
  void testCreateComment() throws Exception {
    mockMvc
        .perform(
            post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"New Comment\", \"post\": {\"id\": " + post.getId() + "}}"))
        .andExpect(status().isOk());
  }
}
