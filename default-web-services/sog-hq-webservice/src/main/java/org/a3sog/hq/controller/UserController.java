package org.a3sog.hq.controller;

import org.a3sog.hq.model.User;
import org.a3sog.hq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
  private final String API_VERSION = "/api/v0.1";
  private final String USER = API_VERSION + "/user" + "/{id}";
  private final String USERS = API_VERSION + "/user";

  @Autowired
  private UserService userService;

  @PostMapping(value = USERS)
  public ResponseEntity<String> saveUser(@RequestBody User user) {
    boolean result = userService.saveUser(user);
    if (result)
      return ResponseEntity.ok("User Created Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping(value = USERS)
  public ResponseEntity<List<User>> fetchAllUser() {
    List<User> users;
    users = userService.fetchAllUser();
    return ResponseEntity.ok(users);
  }

  @GetMapping(value = USER)
  public ResponseEntity<User> fetchUserById(@PathVariable("id") Long id) {
    User user;
    user = userService.fetchUserById(id);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping(value = USER)
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
    boolean result = userService.deleteUser(id);
    if (result)
      return ResponseEntity.ok("User Deleted Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @PutMapping(value = USER)
  public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    boolean result = userService.updateUser(id, user);
    if (result)
      return ResponseEntity.ok("User Updated Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
