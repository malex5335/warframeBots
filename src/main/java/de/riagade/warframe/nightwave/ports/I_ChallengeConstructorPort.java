package de.riagade.warframe.nightwave.ports;

import de.riagade.warframe.nightwave.entities.Challenge;

import java.util.List;

public interface I_ChallengeConstructorPort {
    List<Challenge> getAllChallenges();
    String constructStringMessage(Challenge challenge);
}
