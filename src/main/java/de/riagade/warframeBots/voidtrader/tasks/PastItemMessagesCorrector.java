package de.riagade.warframeBots.voidtrader.tasks;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.RegexHelper;
import de.riagade.warframeBots.voidtrader.util.ShopItemHelper;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;
import java.util.TimerTask;
import java.util.regex.Pattern;

@Getter
@Setter
public class PastItemMessagesCorrector extends TimerTask {
    private BasicBot bot;

    public PastItemMessagesCorrector(BasicBot bot){
        setBot(bot);
    }

    @Override
    public void run() {
        List<String> keys = ShopItemHelper.getKeys();
        for (Message message : getBot().retrieveChannelMessages()) {
            String newText = message.getContentRaw();
            if(newText.contains("/Lotus/")) {
                boolean changedSomething = Boolean.FALSE;
                for (String key : keys) {
                    if (newText.contains(key)) {
                        assert ShopItemHelper.getDescription(key) != null;
                        if(RegexHelper.containsExactSubstring(newText, key)) {
                            newText = newText.replace(key, ShopItemHelper.getDescription(key));
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
