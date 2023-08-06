package org.sog.client.character;

public enum CharacterAceRole {
    ACE_ENGINEER("ace3_role_engineer"),
    ACE_MEDIC("ace3_role_medic"),
    ACE_NONE("");

    private final String role;

    CharacterAceRole(String role) {
        this.role = role;
    }

    public String toString() {
        return role;
    }
}
