package org.sog.client.character;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService service;

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody Character character
    ) {
        return ResponseEntity.ok(service.save(character));
    }
    @GetMapping
    public ResponseEntity<List<Character>> findAllCharacters() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/user/{uid}")
    public ResponseEntity<List<Character>> findAllCharacters(
            @PathVariable("uid") String uid
    ) {
        return ResponseEntity.ok(service.findAllCharactersByUid(uid));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Character> findById(
            @PathVariable("id") String characterId
    ) {
        return ResponseEntity.ok(service.findById(characterId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") String characterId
    ) {
        service.delete(characterId);
        return ResponseEntity.accepted().build();
    }
}
