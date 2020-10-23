package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.voidtrader.util.ShopItemHelper;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.*;

import java.util.*;

@Getter
@Setter
public class PastMessageCorrector extends TimerTask {
    private BasicBot bot;

    public PastMessageCorrector(BasicBot bot){
        setBot(bot);
    }

    @Override
    public void run() {
        MessageHistory.MessageRetrieveAction history = Objects.requireNonNull(getBot().getJda()
                .getTextChannelById(getBot().getChannelId())).getHistoryFromBeginning(100);
        List<String> keys = ShopItemHelper.getKeys();
        for(Message message : history.complete().getRetrievedHistory()) {
            String newText = message.getContentRaw();
            for(String key : keys) {
                if(newText.contains(key)) {
                    newText = newText.replace(key, ShopItemHelper.getDescription(key));
                }
            }
            getBot().editMessage(message.getId(), newText);
        }
    }
}
