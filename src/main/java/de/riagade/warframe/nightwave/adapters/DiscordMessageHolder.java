package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.ports.I_MessageHolderPort;
import de.riagade.warframe.util.BasicBot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;

import java.util.HashMap;
import java.util.Map;

@Getter(AccessLevel.PRIVATE)
@Setter
public class DiscordMessageHolder implements I_MessageHolderPort {

    private BasicBot discordBot;

    public DiscordMessageHolder(BasicBot discordBot) {
        setDiscordBot(discordBot);
    }

    @Override
    public Map<String, String> getLastMessages(int amount) {
        var lastMessages = new HashMap<String, String>();
        for (Message message : getDiscordBot().retrieveChannelMessages(100)) {
            lastMessages.put(message.getId(), message.getContentRaw());
        }
        return lastMessages;
    }
}
