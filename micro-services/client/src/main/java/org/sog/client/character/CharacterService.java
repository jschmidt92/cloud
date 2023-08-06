package org.sog.client.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository repository;

    public String save(Character character) {
        return repository.save(character).getId();
    }
    public Character findById(String id) {
        return repository.findById(id).orElse(null);
    }
    public Character findByUid(String uid) {
        return repository.findByUid(uid).orElse(null);
    }
    public List<Character> findAll() {
        return repository.findAll();
    }
    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<Character> findAllCharactersByUid(String uid) {
        return repository.findAllByUid(uid);
    }
}
