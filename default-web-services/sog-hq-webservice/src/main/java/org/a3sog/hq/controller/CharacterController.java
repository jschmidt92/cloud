package org.a3sog.hq.controller;

import org.a3sog.hq.model.Character;
import org.a3sog.hq.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CharacterController {
  private final String API_VERSION = "/api/v0.1";
  private final String BLOG = API_VERSION + "/character" + "/{id}";
  private final String BLOGS = API_VERSION + "/character";

  @Autowired
  private CharacterService characterService;

  @PostMapping(value = BLOGS)
  public ResponseEntity<String> saveCharacter(@RequestBody Character character) {
    boolean result = characterService.saveCharacter(character);
    if (result)
      return ResponseEntity.ok("Character Created Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping(value = BLOGS)
  public ResponseEntity<List<Character>> fetchAllCharacter() {
    List<Character> characters;
    characters = characterService.fetchAllCharacter();
    return ResponseEntity.ok(characters);
  }

  @GetMapping(value = BLOG)
  public ResponseEntity<Character> fetchCharacterById(@PathVariable("id") Long id) {
    Character character;
    character = characterService.fetchCharacterById(id);
    return ResponseEntity.ok(character);
  }

  @DeleteMapping(value = BLOG)
  public ResponseEntity<String> deleteCharacter(@PathVariable("id") Long id) {
    boolean result = characterService.deleteCharacter(id);
    if (result)
      return ResponseEntity.ok("Character Deleted Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @PutMapping(value = BLOG)
  public ResponseEntity<String> updateCharacter(@PathVariable("id") Long id, @RequestBody Character character) {
    boolean result = characterService.updateCharacter(id, character);
    if (result)
      return ResponseEntity.ok("Character Updated Successfully!!");
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
