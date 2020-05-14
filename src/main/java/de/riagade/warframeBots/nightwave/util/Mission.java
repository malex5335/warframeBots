package de.riagade.warframeBots.nightwave.util;

import de.riagade.warframeBots.BasicBot;

public class Mission {
    private String name, description;
    private E_MissionType missionType;

    public Mission(String name, String description, E_MissionType missionType){
        setName(name);
        setDescription(description);
        setMissionType(missionType);
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

    public void setMissionType(E_MissionType missionType) {
        this.missionType = missionType;
    }

    public void sendMessage(BasicBot bot) {
        switch (getMissionType()) {
            case DAILY:
                bot.sendMessage("Daily: " + getDescription());
                break;
            case WEEKLY:
                bot.sendMessage("Weekly: " + getDescription());
                break;
            case ELITE:
                bot.sendMessage("Elite: " + getDescription());
                break;
        }
    }
}
