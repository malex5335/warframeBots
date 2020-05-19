package de.riagade.warframeBots.nightwave.util;

import de.riagade.warframeBots.util.BasicBot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Challenge {
    private String name, description, standing;
    private E_ChallengeType missionType;
    private Date expireDate;

    public Challenge(String name, Date expireDate){
        setName(name);
        setDescription(ChallengeHelper.getDescription(getName()));
        setStanding(ChallengeHelper.getStanding(getName()));
        setMissionType(ChallengeHelper.getMissionType(getName()));
        setExpireDate(expireDate);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getStanding() {
        return standing;
    }

    private void setStanding(String standing) {
        this.standing = standing;
    }

    public E_ChallengeType getMissionType() {
        return missionType;
    }

    private void setMissionType(E_ChallengeType missionType) {
        this.missionType = missionType;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    private void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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
        messageBuilder.append("+ ").append(getDescription()).append("\n");
        messageBuilder.append("+ ").append(getStanding()).append(" Standing\n");
        messageBuilder.append("- ").append(dateFormat.format(getExpireDate())).append("\n");
        messageBuilder.append("```");
        bot.sendMessage(messageBuilder.toString());
    }
}
