package de.riagade.warframeBots.nightwave.tasks;

import de.riagade.warframeBots.nightwave.util.ChallengeHelper;
import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.RegexHelper;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;
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
        List<String> keys = ChallengeHelper.getKeys();
        for (Message message : getBot().retrieveChannelMessages()) {
            String newText = message.getContentRaw();
            if(newText.contains("/Lotus/")) {
                boolean changedSomething = Boolean.FALSE;
                for (String key : keys) {
                    if (newText.contains(key)) {
                        assert ChallengeHelper.getDescription(key) != null;
                        if(RegexHelper.containsExactSubstring(newText, key)) {
                            newText = newText.replace(key, ChallengeHelper.getDescription(key));
                            changedSomething = Boolean.TRUE;
                        }
                    }
                }
                if (changedSomething) {
                    getBot().editMessage(message.getId(), newText);
                }
            }
        }
    }
}
