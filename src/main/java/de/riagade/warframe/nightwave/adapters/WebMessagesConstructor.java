package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.ports.I_MessageConstructorPort;
import de.riagade.warframe.util.GenericJSONParser;
import de.riagade.warframe.util.RegexHelper;
import org.json.JSONObject;

import java.io.IOException;

public class WebMessagesConstructor implements I_MessageConstructorPort {

    @Override
    public String replaceReferences(String message) {
        try {
            JSONObject challenges = GenericJSONParser
                    .retrieveJSONFromResource("nightwave.json")
                    .getJSONObject("challenges");
            var keys = challenges.keys();
            while(keys.hasNext()) {
                var key = keys.next();
                if (message.contains(key)) {
                    if(RegexHelper.containsExactSubstring(message, key)) {
                        var description = challenges.getJSONObject(key).getString("desc");
                        return message.replace(key, description);
                    }
                }
            }
        } catch (IOException e) {
            // TODO: warn me or something
        }
        return message;
    }
}
