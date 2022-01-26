package de.riagade.warframe.nightwave.ports;

import de.riagade.warframe.nightwave.entities.Challenge;
import de.riagade.warframe.nightwave.entities.MessageId;

import java.util.List;

public interface I_MessageHolderPort {
    List<MessageId> getLastMessages(int amount);
    MessageId postChallenge(Challenge challenge);
    boolean updateMessage(MessageId messageId);
    boolean messageNeedsUpdate(MessageId messageId);
    boolean wasPostedWithin(Challenge challenge, int amount);
}
