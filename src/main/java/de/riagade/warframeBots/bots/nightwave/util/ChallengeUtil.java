package de.riagade.warframeBots.bots.nightwave.util;

import com.google.inject.internal.util.Lists;
import de.riagade.warframeBots.bots.nightwave.util.enums.E_ChallengeType;
import de.riagade.warframeBots.util.GenericJSONParser;
import lombok.experimental.UtilityClass;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@UtilityClass
public class ChallengeUtil {
    public static final String WORLD_STATE = "http://content.warframe.com/dynamic/worldState.php";
    public static final String CHALLENGE_DATA = "nightwave.json";

    public static List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        try{
            JSONObject challenges = GenericJSONParser.retrieveJSONFromResource(ChallengeUtil.CHALLENGE_DATA)
                    .getJSONObject("challenges");
            keys.addAll(Lists.newArrayList(challenges.keys()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return keys;
    }

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

    public static boolean isDaily(String key){
        try {
            JSONObject challenge = getChallenge(key);
            if(!challenge.isEmpty()) {
                if (challenge.has("daily")) {
                    return challenge.getBoolean("daily");
                } else {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.contains("/Daily/");
    }

    public static boolean isWeekly(String key){
        try {
            JSONObject challenge = getChallenge(key);
            if(!challenge.isEmpty()) {
                if (challenge.has("weekly")) {
                    return challenge.getBoolean("weekly");
                } else {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.contains("/Weekly/");
    }

    public static boolean isElite(String key){
        try {
            JSONObject challenge = getChallenge(key);
            if(!challenge.isEmpty()) {
                if (challenge.has("elite")) {
                    return challenge.getBoolean("elite");
                } else {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.contains("/WeeklyHard/");
    }

    public static String getStanding(String key) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        int value = 0;
        try {
            JSONObject standing = GenericJSONParser.retrieveJSONFromResource(ChallengeUtil.CHALLENGE_DATA)
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
            JSONObject challenges = GenericJSONParser.retrieveJSONFromResource(ChallengeUtil.CHALLENGE_DATA)
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

    public static List<ChallengeDao> challengeList(Locale locale, E_ChallengeType... challengeTypes) {
        List<ChallengeDao> challengeDaoList = new ArrayList<>();
        try {
            JSONObject object = GenericJSONParser.retrieveJSONObject(WORLD_STATE);
            JSONObject seasonInfo = object.getJSONObject("SeasonInfo");
            JSONArray activeChallenges = seasonInfo.getJSONArray("ActiveChallenges");
            for(int i = 0; i < activeChallenges.length(); i++){
                JSONObject challenge = activeChallenges.getJSONObject(i);
                String name = challenge.getString("Challenge");
                String expiry = challenge.getJSONObject("Expiry").getJSONObject("$date").getString("$numberLong");
                Calendar expireDate = Calendar.getInstance(locale);
                if(StringUtils.isNumeric(expiry)) {
                    expireDate.setTimeInMillis(Long.parseLong(expiry));
                }
                for(E_ChallengeType challengeType : challengeTypes) {
                    if(ChallengeUtil.representsType(name, challengeType)) {
                        challengeDaoList.add(new ChallengeDao(name,
                                expireDate.getTime()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return challengeDaoList;
    }

    private static boolean representsType(String name, E_ChallengeType challengeType) {
        switch (challengeType) {
            case DAILY:
                return isDaily(name);
            case WEEKLY:
                return isWeekly(name);
            case ELITE:
                return isElite(name);
            default:
                return false;
        }
    }
}
