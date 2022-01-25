package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.ports.I_MessageSpreaderPort;
import de.riagade.warframe.util.BasicBot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PRIVATE)
@Setter
public class DiscordMessageSpreader implements I_MessageSpreaderPort {

    private BasicBot discordBot;

    public DiscordMessageSpreader(BasicBot discordBot) {
        setDiscordBot(discordBot);
    }

    @Override
    public boolean postMessage(String message) {
        try {
            getDiscordBot().sendMessage(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateMessage(String id, String newMessage) {
        try {
            getDiscordBot().editMessage(id, newMessage);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
