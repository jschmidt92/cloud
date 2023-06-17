package org.a3sog.hq.service;

import org.a3sog.hq.model.Character;

import java.util.List;

public interface CharacterService {
  boolean saveCharacter(Character character);

  List<Character> fetchAllCharacter();

  Character fetchCharacterById(Long id);

  boolean deleteCharacter(Long id);

  boolean updateCharacter(Long id, Character character);
}
