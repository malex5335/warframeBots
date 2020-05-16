package de.riagade.warframeBots.nightwave.util;

import de.riagade.warframeBots.util.BasicBot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mission {
    private String name, description;
    private E_MissionType missionType;
    private Date expireDate;

    public Mission(String name, String description, E_MissionType missionType, Date expireDate){
        setName(name);
        setDescription(description);
        setMissionType(missionType);
        setExpireDate(expireDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public E_MissionType getMissionType() {
        return missionType;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setMissionType(E_MissionType missionType) {
        this.missionType = missionType;
    }

    public void sendMessage(BasicBot bot) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm z", bot.getLocale());
        StringBuilder messageBuilder = new StringBuilder();
        switch (getMissionType()) {
            case DAILY:
                messageBuilder.append("```ini\n");
                messageBuilder.append("[Daily]\n");
                break;
            case WEEKLY:
                messageBuilder.append("```fix\n");
                messageBuilder.append("[Weekly]\n");
                break;
            case ELITE:
                messageBuilder.append("```css\n");
                messageBuilder.append("[Elite]\n");
                break;
            default:
                messageBuilder.append("```diff\n");
                messageBuilder.append("- MISSION_TYPE\n");
                break;
        }
        messageBuilder.append("``````diff\n");
        messageBuilder.append("+ " + getDescription() + "\n");
        messageBuilder.append("+ " + ChallengeHelper.getStanding(getMissionType()) + "\n");
        messageBuilder.append("- " + dateFormat.format(getExpireDate()) + "\n");
        messageBuilder.append("```");
    }
}
