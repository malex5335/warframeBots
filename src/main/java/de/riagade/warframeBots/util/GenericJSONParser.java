package de.riagade.warframeBots.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GenericJSONParser {

    public static JSONObject getJSONObject(String jsonLocation) throws Exception {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(jsonLocation);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();
        return new JSONObject(sb.toString());
    }
}