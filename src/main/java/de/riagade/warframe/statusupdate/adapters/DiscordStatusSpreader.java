package de.riagade.warframe.statusupdate.adapters;

import de.riagade.warframe.statusupdate.entities.E_Status;
import de.riagade.warframe.statusupdate.ports.I_StatusSpreaderPort;
import de.riagade.warframe.util.BasicBot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PRIVATE)
@Setter
public class DiscordStatusSpreader implements I_StatusSpreaderPort {

    private BasicBot discordBot;

    public DiscordStatusSpreader(BasicBot discordBot) {
        setDiscordBot(discordBot);
    }

    @Override
    public boolean updateStatus(E_Status newStatus) {
        return false;
    }
}
