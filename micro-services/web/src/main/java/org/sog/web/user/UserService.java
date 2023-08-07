package org.sog.web.user;

import lombok.RequiredArgsConstructor;
import org.sog.web.client.CharacterClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CharacterClient client;

    public void createUser(UserRequest userRequest) {
        User user = User.builder()
                .uid(userRequest.getUid())
                .username(userRequest.getUsername())
                .build();

        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .uid(user.getUid())
                .username(user.getUsername())
                .build();
    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for id: " + id));
        return mapToUserResponse(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    public FullUserResponse findUsersWithCharacters(String uid) {
        var user = userRepository.findByUid(uid)
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
}
