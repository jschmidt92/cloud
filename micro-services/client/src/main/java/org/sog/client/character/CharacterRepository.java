package org.sog.client.character;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends MongoRepository<Character, String> {
    List<Character> findAllByUid(String uid);

    Optional<Character> findByUid(String uid);
}
