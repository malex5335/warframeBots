package de.riagade.warframe.nightwave.entities;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;

@Data
public class Challenge {
    private String name;
    private String description;
    private String standing;
    private E_ChallengeType challengeType;
    private LocalDateTime expireDate;
}
