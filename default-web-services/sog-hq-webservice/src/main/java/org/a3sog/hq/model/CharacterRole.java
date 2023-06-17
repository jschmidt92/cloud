package org.a3sog.hq.model;

public enum CharacterRole {
  LEADER("sog_role_leader"),
  MEDIC("sog_role_medic"),
  ENGINEER("sog_role_engineer"),
  DMR("sog_role_dmr"),
  MG("sog_role_mg");

  private final String role;

  CharacterRole(String role) {
    this.role = role;
  }

  public String toString() {
    return role;
  }
}
