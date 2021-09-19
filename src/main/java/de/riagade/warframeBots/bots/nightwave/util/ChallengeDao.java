package de.riagade.warframeBots.bots.nightwave.util;

import de.riagade.genericDiscordBot.A_BasicBot;
import de.riagade.warframeBots.bots.nightwave.util.enums.E_ChallengeType;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ChallengeDao {
    private String name;
    private String description;
    private String standing;
    private E_ChallengeType missionType;
    private Date expireDate;

    public ChallengeDao(String name, Date expireDate){
        setName(name);
        setDescription(ChallengeUtil.getDescription(getName()));
        setStanding(ChallengeUtil.getStanding(getName()));
        setMissionType(ChallengeUtil.getMissionType(getName()));
        setExpireDate(expireDate);
    }

    public void sendMessage(A_BasicBot bot) {
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
