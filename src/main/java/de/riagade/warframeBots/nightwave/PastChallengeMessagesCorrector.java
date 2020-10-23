package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.nightwave.util.ChallengeHelper;
import de.riagade.warframeBots.util.BasicBot;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;

import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

@Getter
@Setter
public class PastChallengeMessagesCorrector extends TimerTask {
    private BasicBot bot;

    public PastChallengeMessagesCorrector(BasicBot bot){
        setBot(bot);
    }

    @Override
    public void run() {
        MessageHistory.MessageRetrieveAction history = Objects.requireNonNull(bot.getJda()
                .getTextChannelById(bot.getChannelId())).getHistoryFromBeginning(100);
        List<String> keys = ChallengeHelper.getKeys();
        for (Message message : history.complete().getRetrievedHistory()) {
            String newText = message.getContentRaw();
            for (String key : keys) {
                if (newText.contains(key)) {
                    assert ChallengeHelper.getDescription(key) != null;
                    newText = newText.replace(key, ChallengeHelper.getDescription(key));
                }
            }
            bot.editMessage(message.getId(), newText);
        }
    }
}