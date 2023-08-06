package org.sog.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(service.save(user));
    }
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
            @PathVariable("id") String userId
    ) {
        return ResponseEntity.ok(service.findById(userId));
    }
    @GetMapping("/characters/{uid}")
    public ResponseEntity<FullUserResponse> findByUid(
            @PathVariable("uid") String uid
    ) {
        return ResponseEntity.ok(service.findUsersWithCharacters(uid));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") String userId
    ) {
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }
}
