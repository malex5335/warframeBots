package de.riagade.warframeBots.util;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.Objects;

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

    public static JSONObject getJSONFromResource(String name) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(GenericJSONParser
                .class.getClassLoader().getResourceAsStream(name))));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();
        return new JSONObject(sb.toString());
    }
}