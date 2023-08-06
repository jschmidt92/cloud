package org.sog.client.character;

public enum CharacterRole {
    DMR("sog_role_dmr"),
    ENGINEER("sog_role_engineer"),
    GRENADIER("sog_role_grenadier"),
    LEADER("sog_role_leader"),
    MEDIC("sog_role_medic"),
    MG("sog_role_mg"),
    NONE(""),
    RIFLEMAN("sog_role_rifleman"),
    SNIPER("sog_role_sniper");

    private final String role;

    CharacterRole(String role) {
        this.role = role;
    }

    public String toString() {
        return role;
    }
}
