package org.a3sog.hq.model;

public enum AceRole {
  ACE_MEDIC("ace3_role_medic"),
  ACE_ENGINEER("ace3_role_engineer");

  private final String roleText;

  AceRole(String roleText) {
    this.roleText = roleText;
  }

  public String toString() {
    return roleText;
  }
}
