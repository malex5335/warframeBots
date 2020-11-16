package de.riagade.warframeBots.nightwave.util;

import de.riagade.warframeBots.util.BasicBot;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Challenge {
    private String name;
    private String description;
    private String standing;
    private E_ChallengeType missionType;
    private Date expireDate;

    public Challenge(String name, Date expireDate){
        setName(name);
        setDescription(ChallengeHelper.getDescription(getName()));
        setStanding(ChallengeHelper.getStanding(getName()));
        setMissionType(ChallengeHelper.getMissionType(getName()));
        setExpireDate(expireDate);
    }

    public void sendMessage(BasicBot bot) {
        // @TODO: a template for this message would be nice
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
