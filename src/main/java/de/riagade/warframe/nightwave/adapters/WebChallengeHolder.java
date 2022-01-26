package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.entities.Challenge;
import de.riagade.warframe.nightwave.entities.E_ChallengeType;
import de.riagade.warframe.nightwave.ports.I_ChallengeHolderPort;
import de.riagade.warframe.util.DateHelper;
import de.riagade.warframe.util.GenericJSONParser;
import de.riagade.warframe.util.RegexHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PRIVATE)
@Setter
public class WebChallengeHolder implements I_ChallengeHolderPort {

    public static final String WORLD_STATE = "http://content.warframe.com/dynamic/worldState.php";

    private ZoneId zoneId;

    public WebChallengeHolder(ZoneId zoneId) {
        setZoneId(zoneId);
    }

    @Override
    public List<Challenge> getActiveChallenges() {
        var challengeList = new ArrayList<Challenge>();
        try {
            var activeChallenges = GenericJSONParser
                    .retrieveJSONObject(WebChallengeHolder.WORLD_STATE)
                    .getJSONObject("SeasonInfo")
                    .getJSONArray("ActiveChallenges");
            for(int i = 0; i < activeChallenges.length(); i++) {
                var challengeJson = activeChallenges.getJSONObject(i);
                var challengeName = challengeJson.getString("Challenge");
                var challengeDescription = ""; // TODO: get description
                var challengeStanding = ""; // TODO: get standing
                var challengeType = getChallengeType(challengeName);
                var challengeExpire = getDateIn(challengeJson.getJSONObject("Expiry"), getZoneId());

                var challenge = new Challenge();
                challenge.setName(challengeName);
                challenge.setDescription(challengeDescription);
                challenge.setStanding(challengeStanding);
                challenge.setChallengeType(challengeType);
                challenge.setExpireDate(challengeExpire);

                challengeList.add(challenge);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return challengeList;
    }

    private E_ChallengeType getChallengeType(String challengeName) {
        if(isDaily(challengeName)) {
            return E_ChallengeType.DAILY;
        } else if(isWeekly(challengeName)) {
            return E_ChallengeType.WEEKLY;
        } else if(isElite(challengeName)) {
            return E_ChallengeType.ELITE;
        }
        return E_ChallengeType.DAILY;
    }

    public boolean isDaily(String key) {
        return reflectsStatus(key, "daily", "/Daily/");
    }

    public boolean isWeekly(String key){
        return reflectsStatus(key, "weekly", "/Weekly/");
    }

    public boolean isElite(String key){
        return reflectsStatus(key, "elite", "/WeeklyHard/");
    }

    public boolean reflectsStatus(String key, String jsonKey, String textKey) {
        try {
            var challenge = new JSONObject();
            var challenges = GenericJSONParser
                    .retrieveJSONFromResource("nightwave.json")
                    .getJSONObject("challenges");
            if(challenges.has(key)) {
                challenge = challenges.getJSONObject(key);
                if (challenge.has(jsonKey)) {
                    return challenge.getBoolean(jsonKey);
                } else {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.contains(textKey);
    }

    private LocalDateTime getDateIn(JSONObject jsonObject, ZoneId zoneId) {
        return DateHelper.parseTimestamp(jsonObject
                        .getJSONObject("$date")
                        .getString("$numberLong"),
                zoneId);
    }
}
