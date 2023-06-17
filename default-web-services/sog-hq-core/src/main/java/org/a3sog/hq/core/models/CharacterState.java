package org.a3sog.hq.core.models;

public enum CharacterState {
  OK("sog_state_ok"),
  HOSTAGE("sog_state_hostage"),
  WOUNDED_IN_ACTION("sog_state_wia"),
  KILLED_IN_ACTION("sog_state_kia");

  private final String state;

  CharacterState(final String state) {
    this.state = state;
  }

  public String toString() {
    return state;
  }
}
