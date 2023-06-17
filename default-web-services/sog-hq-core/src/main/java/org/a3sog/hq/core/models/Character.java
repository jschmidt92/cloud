package org.a3sog.hq.core.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
  // Meta
  @Id
  private String uid;
  private int slot;
  private String name;
  private CharacterRole role;
  // State
  private CharacterState state;
  private String position;
  private String timeStampWIA;
  // Equipment
  private String loadout;
  // Scores
  private int reputation;
  private int bank;
  private int cash;
  // ACE3
  private AceRole aceRole;
}
