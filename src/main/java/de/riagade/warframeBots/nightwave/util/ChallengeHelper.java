package de.riagade.warframeBots.nightwave.util;

import de.riagade.warframeBots.util.GenericJSONParser;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ChallengeHelper {
    public static final String CHALLENGE_DATA = "nightwave.json";

    public static String getDescription(String key){
        try {
            JSONObject challenge = getChallenge(key);
            if (!challenge.isEmpty()){
                return challenge.getString("desc");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }

    public static boolean isDaily(String key){
        try {
            JSONObject challenge = getChallenge(key);
            if (!challenge.isEmpty()){
                return challenge.getBoolean("daily");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public static boolean isWeekly(String key){
        try {
            JSONObject challenge = getChallenge(key);
            if (!challenge.isEmpty()){
                return challenge.getBoolean("weekly");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public static boolean isElite(String key){
        try {
            JSONObject challenge = getChallenge(key);
            if (!challenge.isEmpty()){
                return challenge.getBoolean("elite");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public static String getStanding(String key) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        int value = 0;
        try {
            JSONObject standing = GenericJSONParser.getJSONFromResource(ChallengeHelper.CHALLENGE_DATA)
                    .getJSONObject("standing");
            if (isDaily(key)){
                value = standing.getInt("daily");
            } else if (isWeekly(key)){
                value = standing.getInt("weekly");
            } else if (isElite(key)){
                value = standing.getInt("elite");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return decimalFormat.format(value);
    }

    private static JSONObject getChallenge(String key){
        JSONObject challenge = new JSONObject();
        try {
            JSONObject challenges = GenericJSONParser.getJSONFromResource(ChallengeHelper.CHALLENGE_DATA)
                    .getJSONObject("challenges");
            if(challenges.has(key)) {
                challenge = challenges.getJSONObject(key);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return challenge;
    }

    public static E_ChallengeType getMissionType(String key) {
        if(isDaily(key)){
            return E_ChallengeType.DAILY;
        } else if(isWeekly(key)){
            return E_ChallengeType.WEEKLY;
        } else if(isElite(key)){
            return E_ChallengeType.ELITE;
        }
        return null;
    }
}