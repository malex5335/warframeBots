package de.riagade.warframeBots.nightwave.util;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.GenericJSONParser;
import lombok.experimental.UtilityClass;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

@UtilityClass
public class NoraHelper {

    public static boolean retrieveActiveState(Locale locale) {
        try {
            JSONObject object = GenericJSONParser.getJSONObject(BasicBot.WORLD_STATE);
            JSONObject seasonInfo = object.getJSONObject("SeasonInfo");
            Calendar activation = NoraHelper.getDateIn(seasonInfo.getJSONObject("Activation"), locale);
            Calendar expiry = NoraHelper.getDateIn(seasonInfo.getJSONObject("Expiry"), locale);
            Calendar now = Calendar.getInstance(locale);
            return now.after(activation) && now.before(expiry);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static Calendar getDateIn(JSONObject object, Locale locale) {
        String numberString = object.getJSONObject("$date").getString("$numberLong");
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTimeInMillis(0);
        if(StringUtils.isNumeric(numberString)) {
            calendar.setTimeInMillis(Long.parseLong(numberString));
        }
        return calendar;
    }

    public static int retrieveSeasonInfo() {
        try {
            JSONObject object = GenericJSONParser.getJSONObject(BasicBot.WORLD_STATE);
            JSONObject seasonInfo = object.getJSONObject("SeasonInfo");
            return seasonInfo.getInt("Season");
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int retrievePhaseInfo() {
        try {
            JSONObject object = GenericJSONParser.getJSONObject(BasicBot.WORLD_STATE);
            JSONObject seasonInfo = object.getJSONObject("SeasonInfo");
            return seasonInfo.getInt("Phase");
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
