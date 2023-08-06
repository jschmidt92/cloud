package org.sog.client.character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@Builder
public class Character {
    @Id
    private String id;
    private String uid;
    private Integer slot;
    private String name;
    private CharacterRole role;
    private CharacterState state;
    private String position;
    private Integer direction;
    private String stance;
    private Date timeStampWIA;
    private String currentWeapon;
    private String loadout;
    private String holster;
    private String paygrade;
    private Integer reputation;
    private Integer bank;
    private Integer cash;
    private CharacterAceRole aceRole;
    private String phoneNumber;
    private List<String> messages;
    private String armoryUnlocks;
    private String garageUnlocks;
    private String garage;
    private String locker;
}
