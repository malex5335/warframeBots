package de.riagade.warframeBots.voidtrader.tasks;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.voidtrader.util.ShopItemHelper;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;

import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

@Getter
@Setter
public class PastItemMessagesCorrector extends TimerTask {
    private BasicBot bot;

    public PastItemMessagesCorrector(BasicBot bot){
        setBot(bot);
    }

    @Override
    public void run() {
        MessageHistory history = Objects.requireNonNull(getBot().getJda()
                .getTextChannelById(getBot().getChannelId())).getHistory();
        List<String> keys = ShopItemHelper.getKeys();
        for (Message message : history.getRetrievedHistory()) {
            String newText = message.getContentRaw();
            boolean changedSomething = Boolean.FALSE;
            for (String key : keys) {
                if (newText.contains(key)) {
                    assert ShopItemHelper.getDescription(key) != null;
                    newText = newText.replace(key, ShopItemHelper.getDescription(key));
                    changedSomething = Boolean.TRUE;
                }
            }
            if(changedSomething) {
                getBot().editMessage(message.getId(), newText);
            }
        }
    }
}
