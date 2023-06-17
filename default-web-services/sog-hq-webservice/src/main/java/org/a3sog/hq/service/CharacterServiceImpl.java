package org.a3sog.hq.service;

import org.a3sog.hq.model.Character;
import org.a3sog.hq.repository.CharacterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

  @Autowired
  private CharacterDao characterDao;

  @Override
  public boolean saveCharacter(Character character) {
    return characterDao.saveCharacter(character);
  }

  @Override
  public List<Character> fetchAllCharacter() {
    return characterDao.fetchAllCharacter();
  }

  @Override
  public Character fetchCharacterById(Long id) {
    return characterDao.fetchCharacterById(id);
  }

  @Override
  public boolean deleteCharacter(Long id) {
    return characterDao.deleteCharacter(id);
  }

  @Override
  public boolean updateCharacter(Long id, Character character) {
    return characterDao.updateCharacter(id, character);
  }
}
