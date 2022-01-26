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
    private I_ChallengeConverterPort challengeConverter;
    private I_MessageSpreaderPort messageSpreader;
    private I_MessageConstructorPort messageConstructor;
    private I_MessageHolderPort messageHolder;

    public NightwaveService(I_ChallengeHolderPort challengeHolder,
                            I_ChallengeConverterPort challengeConverter,
                            I_MessageSpreaderPort messageSpreader,
                            I_MessageConstructorPort messageConstructor,
                            I_MessageHolderPort messageHolder){
        setChallengeHolder(challengeHolder);
        setChallengeConverter(challengeConverter);
        setMessageSpreader(messageSpreader);
        setMessageConstructor(messageConstructor);
        setMessageHolder(messageHolder);
    }

    public void postChallenges(boolean onlyPostLast) throws MessagesNotSentException {
        var challengeList = getChallengeHolder().getActiveChallenges();
        if(onlyPostLast) {
            challengeList = Collections.singletonList(challengeList.get(challengeList.size() - 1));
        }
        for(var challenge : challengeList) {
            var challengeMessage = getChallengeConverter().convertToMessage(challenge);
            if(!getMessageHolder().getLastMessages(100).containsValue(challengeMessage)) {
                if (!getMessageSpreader().postMessage(challengeMessage)) {
                    log.error(String.format("failed to post challenge %s", challenge));
                    throw new MessagesNotSentException("one or more messages could not be sent");
                }
            }
        }
    }

    public void updateLastMessages() throws MessagesNotSentException {
        for(var messageEntry : getMessageHolder().getLastMessages(100).entrySet()) {
            var updatedMessage = getMessageConstructor().replaceReferences(messageEntry.getValue());
            if(!getMessageSpreader().updateMessage(messageEntry.getKey(), updatedMessage)) {
                log.error(String.format("failed to update message with key %s to %s", messageEntry.getKey(), updatedMessage));
                throw new MessagesNotSentException("one or more messages could not be updated");
            }
        }
    }
}
