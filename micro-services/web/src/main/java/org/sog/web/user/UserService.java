package org.sog.web.user;

import lombok.RequiredArgsConstructor;
import org.sog.web.client.CharacterClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final CharacterClient client;

    public String save(User user) {
        return repository.save(user).getId();
    }
    public User findById(String id) {
        return repository.findById(id).orElse(null);
    }
    public User findByUid(String uid) {
        return repository.findByUid(uid).orElse(null);
    }
    public List<User> findAll() {
        return repository.findAll();
    }
    public FullUserResponse findUsersWithCharacters(String uid) {
        var user = repository.findByUid(uid)
                .orElse(
                        User.builder()
                                .id("NOT_FOUND")
                                .uid("NOT_FOUND")
                                .username("NOT_FOUND")
                                .build()
                );
        var characters = client.findAllCharactersByUid(uid);
        return FullUserResponse.builder().id(user.getId()).uid(user.getUid()).username(user.getUsername()).characters(characters).build();
    }
    public void delete(String id) {
        repository.deleteById(id);
    }
}
