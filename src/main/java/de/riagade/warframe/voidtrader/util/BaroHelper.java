package de.riagade.warframe.voidtrader.util;

import de.riagade.warframe.util.BasicBot;
import de.riagade.warframe.util.GenericJSONParser;
import lombok.experimental.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;

@UtilityClass
public class BaroHelper {
    public static boolean retrieveActiveState() {
        try {
            JSONObject object = GenericJSONParser.retrieveJSONObject(BasicBot.WORLD_STATE);
            if(object.has("VoidTraders")) {
                JSONArray voidTraders = object.getJSONArray("VoidTraders");
                JSONObject firstEntry = voidTraders.getJSONObject(0);
                if(firstEntry.has("Manifest")) {
                    JSONArray manifest = firstEntry.getJSONArray("Manifest");
                    return manifest.length() > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String retrieveShopLocation() {
        if(BaroHelper.retrieveActiveState()) {
            try {
                JSONObject object = GenericJSONParser.retrieveJSONObject(BasicBot.WORLD_STATE);
                if (object.has("VoidTraders")) {
                    JSONArray voidTraders = object.getJSONArray("VoidTraders");
                    JSONObject firstEntry = voidTraders.getJSONObject(0);
                    if (firstEntry.has("Node")) {
                        String node = firstEntry.getString("Node");
                        return node.replace("HUB", " Relay");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
