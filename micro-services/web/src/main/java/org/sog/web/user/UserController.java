package org.sog.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBlog(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllBlogs() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserResponse findBlogById(
            @PathVariable("id") String id
    ) {
        return userService.getUserById(id);
    }

    @GetMapping("/characters/{uid}")
    public ResponseEntity<FullUserResponse> findByUid(
            @PathVariable("uid") String uid
    ) {
        return ResponseEntity.ok(userService.findUsersWithCharacters(uid));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBlog(
            @PathVariable("id") String id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
