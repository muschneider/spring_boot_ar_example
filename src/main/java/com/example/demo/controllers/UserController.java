package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping
  public List<Map<String, Object>> getUsers() {
    List<User> users = new User().findAll();
    List<Map<String, Object>> usersMap = new ArrayList<>();
    for (User user : users) {
      usersMap.add(user.getUserMap());
    }
    return usersMap;
  }

  @PostMapping
  @Transactional
  public User createUser(@RequestBody final User user) {
    return user.save();
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable final Long id) {
    User user = new User();
    user.setId(id);
    user.delete();
  }

  @GetMapping("username/{username}")
  public Map<String, Object> getUserByUsername(@PathVariable final String username) {
    List<User> users = new User().findUserByUsername(username);
    if (users != null && users.size() > 0) {
      return users.getFirst().getUserMap();
    } else throw new UserNotFoundException("User not found with username: " + username);
  }

  @GetMapping("email/{email}")
  public Map<String, Object> getUserByemail(@PathVariable final String email) {
    User user =
        new User()
            .findUserByEmail(email)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        "User not found with email: " + email)); // Throw if not found

    return user.getUserMap();
  }
}
