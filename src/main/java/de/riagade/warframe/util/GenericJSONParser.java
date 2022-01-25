package de.riagade.warframe.util;

import lombok.experimental.UtilityClass;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.Objects;

@UtilityClass
public class GenericJSONParser {

    /**
     * reads JSON from an URL-Source
     *
     * @param jsonLocation URL of the JSON-String
     * @return {@link JSONObject}
     * @throws IOException if there are problems reading lines from the JSON-String
     */
    public static JSONObject retrieveJSONObject(String jsonLocation) throws IOException {
        URL url = new URL(jsonLocation);
        return retrieveJSONFromBufferedReader(new BufferedReader(
                new InputStreamReader(url.openStream())));
    }

    /**
     * reads JSON from a local resource
     *
     * @param name resource path to the JSON-String
     * @return {@link JSONObject}
     * @throws IOException if there are problems reading lines from the JSON-String
     */
    public static JSONObject retrieveJSONFromResource(String name) throws IOException {
        return retrieveJSONFromBufferedReader(new BufferedReader(new InputStreamReader(Objects.requireNonNull(GenericJSONParser
                .class.getClassLoader().getResourceAsStream(name)))));
    }

    /**
     * extracts a JSON-Object using a BufferedReader
     *
     * @param in the {@link BufferedReader} to use
     * @return {@link JSONObject}
     * @throws IOException if there are problems reading lines from the JSON-String
     */
    private static JSONObject retrieveJSONFromBufferedReader(BufferedReader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();
        return new JSONObject(sb.toString());
    }
}
