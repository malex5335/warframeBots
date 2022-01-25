package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.entities.Challenge;
import de.riagade.warframe.nightwave.ports.I_ChallengeConstructorPort;

import java.util.List;

public class WebChallengeConstructor implements I_ChallengeConstructorPort {

    @Override
    public List<Challenge> getAllChallenges() {
        return null;
    }

    @Override
    public String constructStringMessage(Challenge challenge) {
        return null;
    }
}
