package de.riagade.warframeBots.nightwave.util;

import de.riagade.warframeBots.util.GenericJSONParser;
import lombok.experimental.UtilityClass;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@UtilityClass
public class ChallengeHelper {
    public static final String CHALLENGE_DATA = "nightwave.json";

    public static String getDescription(String key) {
        try {
            JSONObject challenge = getChallenge(key);
            if (!challenge.isEmpty()) {
                return challenge.getString("desc");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    private static boolean isDaily(String key, E_ChallengeType before){
        try {
            JSONObject challenge = getChallenge(key);
            if(!challenge.isEmpty()) {
                if (challenge.has("daily")) {
                    return challenge.getBoolean("daily");
                }
                if(!before.equals(E_ChallengeType.ELITE) && isElite(key, E_ChallengeType.DAILY)) {
                    return Boolean.FALSE;
                }
                if (!before.equals(E_ChallengeType.WEEKLY) && isWeekly(key, E_ChallengeType.DAILY)) {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.contains("/Daily/");
    }

    public static boolean isDaily(String key) {
        return isDaily(key, E_ChallengeType.DAILY);
    }

    private static boolean isWeekly(String key, E_ChallengeType before){
        try {
            JSONObject challenge = getChallenge(key);
            if(!challenge.isEmpty()) {
                if (challenge.has("weekly")) {
                    return challenge.getBoolean("weekly");
                }
                if(!before.equals(E_ChallengeType.ELITE) && isElite(key, E_ChallengeType.WEEKLY)) {
                    return Boolean.FALSE;
                }
                if (!before.equals(E_ChallengeType.DAILY) && isDaily(key, E_ChallengeType.WEEKLY)) {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.contains("/Weekly/");
    }

    public static boolean isWeekly(String key) {
        return isWeekly(key, E_ChallengeType.WEEKLY);
    }

    private static boolean isElite(String key, E_ChallengeType before){
        try {
            JSONObject challenge = getChallenge(key);
            if(!challenge.isEmpty()) {
                if (challenge.has("elite")) {
                    return challenge.getBoolean("elite");
                }
                if (!before.equals(E_ChallengeType.DAILY) && isDaily(key, E_ChallengeType.ELITE)) {
                    return Boolean.FALSE;
                }
                if (!before.equals(E_ChallengeType.WEEKLY) && isWeekly(key, E_ChallengeType.ELITE)) {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.contains("/WeeklyHard/");
    }

    public static boolean isElite(String key) {
        return isElite(key, E_ChallengeType.ELITE);
    }

    public static String getStanding(String key) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        int value = 0;
        try {
            JSONObject standing = GenericJSONParser.getJSONFromResource(ChallengeHelper.CHALLENGE_DATA)
                    .getJSONObject("standing");
            if (isDaily(key)) {
                value = standing.getInt("daily");
            } else if (isWeekly(key)) {
                value = standing.getInt("weekly");
            } else if (isElite(key)) {
                value = standing.getInt("elite");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decimalFormat.format(value);
    }

    private static JSONObject getChallenge(String key) {
        JSONObject challenge = new JSONObject();
        try {
            JSONObject challenges = GenericJSONParser.getJSONFromResource(ChallengeHelper.CHALLENGE_DATA)
                    .getJSONObject("challenges");
            if(challenges.has(key)) {
                challenge = challenges.getJSONObject(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return challenge;
    }

    public static E_ChallengeType getMissionType(String key) {
        if(isDaily(key)) {
            return E_ChallengeType.DAILY;
        } else if(isWeekly(key)) {
            return E_ChallengeType.WEEKLY;
        } else if(isElite(key)) {
            return E_ChallengeType.ELITE;
        }
        return null;
    }
}