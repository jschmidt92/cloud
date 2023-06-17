package org.a3sog.hq.repository;

import org.a3sog.hq.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterDaoImpl implements CharacterDao {

  @Autowired
  private RedisTemplate redisTemplate;

  private static final String KEY = "BLOG";

  @Override
  public boolean saveCharacter(Character character) {
    try {
      redisTemplate.opsForHash().put(KEY, character.getUid().toString(), character);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<Character> fetchAllCharacter() {
    List<Character> characters;
    characters = redisTemplate.opsForHash().values(KEY);
    return characters;
  }

  @Override
  public Character fetchCharacterById(Long id) {
    Character character;
    character = (Character) redisTemplate.opsForHash().get(KEY, id.toString());
    return character;
  }

  @Override
  public boolean deleteCharacter(Long id) {
    try {
      redisTemplate.opsForHash().delete(KEY, id.toString());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateCharacter(Long id, Character character) {
    try {
      redisTemplate.opsForHash().put(KEY, id, character);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
