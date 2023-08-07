package org.sog.web.client;

import org.sog.web.user.Character;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "character-service", url = "http://localhost:8080/api/v1/characters")
@FeignClient(name = "character-service", url = "${application.config.characters-url}")
public interface CharacterClient {
    @GetMapping("/user/{uid}")
    List<Character> findAllCharactersByUid(@PathVariable("uid") String uid);
}
