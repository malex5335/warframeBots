package de.riagade.warframe.status.adapters;

import de.riagade.warframe.status.entities.E_Status;
import de.riagade.warframe.status.ports.I_StatusSpreaderPort;
import de.riagade.warframe.util.BasicBot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

@Getter(AccessLevel.PRIVATE)
@Setter
public class DiscordStatusSpreader implements I_StatusSpreaderPort {

    private BasicBot discordBot;

    public DiscordStatusSpreader(BasicBot discordBot) {
        setDiscordBot(discordBot);
    }

    @Override
    public boolean updateStatus(E_Status status, String message) {
        try {
            switch (status) {
                case ACTIVE:
                    getDiscordBot().overwriteActivity(Activity.watching(message));
                    getDiscordBot().overwriteOnlineStatus(OnlineStatus.ONLINE);
                    getDiscordBot().overwriteIdle(false);
                    break;
                case WAITING:
                    getDiscordBot().overwriteActivity(Activity.listening(message));
                    getDiscordBot().overwriteOnlineStatus(OnlineStatus.DO_NOT_DISTURB);
                    getDiscordBot().overwriteIdle(true);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
