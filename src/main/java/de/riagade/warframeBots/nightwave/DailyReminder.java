package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.nightwave.util.ChallengeDescriptor;
import de.riagade.warframeBots.nightwave.util.E_MissionType;
import de.riagade.warframeBots.nightwave.util.Mission;
import de.riagade.warframeBots.util.GenericJSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class DailyReminder extends TimerTask {
    private BasicBot bot;

    public DailyReminder(BasicBot bot) {
        setBot(bot);
    }

    public BasicBot getBot() {
        return bot;
    }

    public void setBot(BasicBot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        List<Mission> missionList = generateMissions("http://content.warframe.com/dynamic/worldState.php");
        for(Mission mission : missionList){
            mission.sendMessage(getBot());
        }
    }

    private List<Mission> generateMissions(String jsonLocation) {
        List<Mission> missionList = new ArrayList<Mission>();
        try {
            JSONObject object = GenericJSONParser.getJSONObject(jsonLocation);
            JSONObject seasonInfo = object.getJSONObject("SeasonInfo");
            JSONArray activeChallenges = seasonInfo.getJSONArray("ActiveChallenges");
            for(int i = 0; i < activeChallenges.length(); i++){
                JSONObject challenge = activeChallenges.getJSONObject(i);
                String name = challenge.getString("Challenge");
                if(name.contains("/Daily/")) {
                    missionList.add(new Mission(name, ChallengeDescriptor.getDescription(name), E_MissionType.DAILY));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return missionList;
    }

}