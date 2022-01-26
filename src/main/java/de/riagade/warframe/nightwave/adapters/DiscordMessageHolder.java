package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.entities.Challenge;
import de.riagade.warframe.nightwave.entities.E_ChallengeType;
import de.riagade.warframe.nightwave.entities.MessageId;
import de.riagade.warframe.nightwave.ports.I_MessageHolderPort;
import de.riagade.warframe.util.BasicBot;
import de.riagade.warframe.util.GenericJSONParser;
import de.riagade.warframe.util.RegexHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;
import org.json.JSONObject;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter(AccessLevel.PRIVATE)
@Setter
public class DiscordMessageHolder implements I_MessageHolderPort {

    private BasicBot discordBot;

    public DiscordMessageHolder(BasicBot discordBot) {
        setDiscordBot(discordBot);
    }

    @Override
    public List<MessageId> getLastMessages(int amount) {
        var lastMessages = new ArrayList<MessageId>();
        for (var message : getDiscordBot().retrieveChannelMessages(100)) {
            var messageId = new DiscordMessageId(message);
            lastMessages.add(messageId);
        }
        return lastMessages;
    }

    @Override
    public MessageId postChallenge(Challenge challenge) {
        try {
            getDiscordBot().sendMessage(convertToMessage(challenge));
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean updateMessage(MessageId messageId) {
        try {
            getDiscordBot().editMessage(messageId.getId(), replaceReferences(getMessageFromId(messageId)));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean messageNeedsUpdate(MessageId messageId) {
        return false;
    }

    @Override
    public boolean wasPostedWithin(Challenge challenge, int amount) {
        return false;
    }

    public String convertToMessage(Challenge challenge) {
        var template = "```%s\n" +
                "[%s]\n" +
                "```" +
                "```diff\n" +
                "+ %s\n" +
                "+ %s\n" +
                "- %s\n" +
                "```";
        return String.format(template,
                getHeadlineForMissionType(challenge.getChallengeType()),
                convertToString(challenge.getChallengeType()),
                challenge.getDescription(),
                challenge.getStanding(),
                challenge.getExpireDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z")));
    }

    private String convertToString(E_ChallengeType challengeType) {
        if(challengeType == null) {
            return "CHALLENGE_TYPE";
        }
        var tmp = challengeType.name();
        return String.valueOf(tmp.charAt(0)).toUpperCase() + tmp.substring(1).toLowerCase();
    }

    private String getHeadlineForMissionType(E_ChallengeType missionType) {
        switch (missionType) {
            case DAILY:
                return "ini";
            case WEEKLY:
                return "fix";
            case ELITE:
                return "css";
            default:
                return "diff";
        }
    }

    public String replaceReferences(String message) {
        try {
            JSONObject challenges = GenericJSONParser
                    .retrieveJSONFromResource("nightwave.json")
                    .getJSONObject("challenges");
            var keys = challenges.keys();
            while(keys.hasNext()) {
                var key = keys.next();
                if (message.contains(key)) {
                    if(RegexHelper.containsExactSubstring(message, key)) {
                        var description = challenges.getJSONObject(key).getString("desc");
                        return message.replace(key, description);
                    }
                }
            }
        } catch (IOException e) {
            // TODO: warn me or something
        }
        return message;
    }

    private String getMessageFromId(MessageId messageId) {
        // TODO: do stuff
        return messageId.getContent();
    }
}
