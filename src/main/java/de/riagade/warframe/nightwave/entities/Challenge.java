package de.riagade.warframe.nightwave.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Challenge {
    private String name;
    private String description;
    private String standing;
    private E_ChallengeType missionType;
    private LocalDateTime expireDate;
}
