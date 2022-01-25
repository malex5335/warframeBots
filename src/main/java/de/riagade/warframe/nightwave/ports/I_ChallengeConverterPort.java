package de.riagade.warframe.nightwave.ports;

import de.riagade.warframe.nightwave.entities.Challenge;

public interface I_ChallengeConverterPort {
    String convertToMessage(Challenge challenge);
}
