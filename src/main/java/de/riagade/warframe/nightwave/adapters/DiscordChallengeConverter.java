package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.entities.Challenge;
import de.riagade.warframe.nightwave.entities.E_ChallengeType;
import de.riagade.warframe.nightwave.ports.I_ChallengeConverterPort;

import java.time.format.DateTimeFormatter;

public class DiscordChallengeConverter implements I_ChallengeConverterPort {

    @Override
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
}
