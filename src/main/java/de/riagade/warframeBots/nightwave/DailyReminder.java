package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.nightwave.util.ChallengeDescriptor;
import de.riagade.warframeBots.nightwave.util.E_MissionType;
import de.riagade.warframeBots.nightwave.util.Mission;
import de.riagade.warframeBots.util.GenericJSONParser;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class DailyReminder extends TimerTask {
    private static final boolean ONLY_REMIND_LAST = Boolean.TRUE;

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
        if(DailyReminder.ONLY_REMIND_LAST) {
            missionList.get(missionList.size() - 1).sendMessage(getBot());
        } else {
            for (Mission mission : missionList) {
                mission.sendMessage(getBot());
            }
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
                String expiry = challenge.getJSONObject("Expiry").getJSONObject("$date").getString("$numberLong");
                Calendar expireDate = Calendar.getInstance(getBot().getLocale());
                if(StringUtils.isNumeric(expiry)) {
                    expireDate.setTimeInMillis(Long.valueOf(expiry));
                }
                if(name.contains("/Daily/")) {
                    missionList.add(new Mission(name,
                            ChallengeDescriptor.getDescription(name),
                            E_MissionType.DAILY,
                            expireDate.getTime()));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return missionList;
    }

}