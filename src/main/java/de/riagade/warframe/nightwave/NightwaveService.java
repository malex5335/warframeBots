package de.riagade.warframe.nightwave;

import de.riagade.warframe.nightwave.exceptions.MessagesNotSentException;
import de.riagade.warframe.nightwave.ports.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Getter(AccessLevel.PRIVATE)
@Setter
@Slf4j
public class NightwaveService {

    private I_ChallengeHolderPort challengeHolder;
    private I_MessageHolderPort messageHolder;

    public NightwaveService(I_ChallengeHolderPort challengeHolder,
                            I_MessageHolderPort messageHolder){
        setChallengeHolder(challengeHolder);
        setMessageHolder(messageHolder);
    }

    public void postChallenges(boolean onlyPostLast) throws MessagesNotSentException {
        var challengeList = getChallengeHolder().getActiveChallenges();
        if(onlyPostLast) {
            challengeList = Collections.singletonList(challengeList.get(challengeList.size() - 1));
        }
        for(var challenge : challengeList) {
            if(!getMessageHolder().wasPostedWithin(challenge, 100)) {
                if (getMessageHolder().postChallenge(challenge) == null) {
                    log.error(String.format("failed to post challenge %s", challenge));
                    throw new MessagesNotSentException("one or more messages could not be sent");
                }
            }
        }
    }

    public void updateLastMessages() throws MessagesNotSentException {
        for(var messageId : getMessageHolder().getLastMessages(100)) {
            if(getMessageHolder().messageNeedsUpdate(messageId)) {
                if (!getMessageHolder().updateMessage(messageId)) {
                    log.error(String.format("failed to update message %s", messageId));
                    throw new MessagesNotSentException("one or more messages could not be updated");
                }
            }
        }
    }
}
