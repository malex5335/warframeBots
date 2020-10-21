package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.nightwave.util.ChallengeHelper;
import de.riagade.warframeBots.nightwave.util.Challenge;
import de.riagade.warframeBots.util.GenericJSONParser;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

@Getter
@Setter
public class DailyReminder extends TimerTask {
    private static final Boolean ONLY_REMIND_LAST = Boolean.TRUE;

    private BasicBot bot;

    public DailyReminder(BasicBot bot){
        setBot(bot);
    }

    @Override
    public void run() {
        List<Challenge> challengeList = generateMissions();
        if(DailyReminder.ONLY_REMIND_LAST) {
            challengeList.get(challengeList.size() - 1).sendMessage(getBot());
        } else {
            for (Challenge challenge : challengeList) {
                challenge.sendMessage(getBot());
            }
        }
    }

    private List<Challenge> generateMissions() {
        List<Challenge> challengeList = new ArrayList<Challenge>();
        try {
            JSONObject object = GenericJSONParser.getJSONObject(BasicBot.WORLD_STATE);
            JSONObject seasonInfo = object.getJSONObject("SeasonInfo");
            JSONArray activeChallenges = seasonInfo.getJSONArray("ActiveChallenges");
            for(int i = 0; i < activeChallenges.length(); i++){
                JSONObject challenge = activeChallenges.getJSONObject(i);
                String name = challenge.getString("Challenge");
                String expiry = challenge.getJSONObject("Expiry").getJSONObject("$date").getString("$numberLong");
                Calendar expireDate = Calendar.getInstance(getBot().getLocale());
                if(StringUtils.isNumeric(expiry)) {
                    expireDate.setTimeInMillis(Long.parseLong(expiry));
                }
                if(ChallengeHelper.isDaily(name)) {
                    challengeList.add(new Challenge(name,
                            expireDate.getTime()));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return challengeList;
    }

}