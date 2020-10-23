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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

@Getter
@Setter
public class WeeklyReminder  extends TimerTask {
    private BasicBot bot;

    public WeeklyReminder(BasicBot bot) {
        setBot(bot);
    }

    @Override
    public void run() {
        List<Challenge> challengeList = generateMissions();
        for(Challenge challenge : challengeList){
            challenge.sendMessage(getBot());
        }
    }

    private List<Challenge> generateMissions() {
        List<Challenge> challengeList = new ArrayList<>();
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
                if(ChallengeHelper.isWeekly(name)){
                    challengeList.add(new Challenge(name,
                            expireDate.getTime()));
                } else if (ChallengeHelper.isElite(name)){
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
