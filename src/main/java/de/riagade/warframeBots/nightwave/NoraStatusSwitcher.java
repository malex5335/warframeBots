package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.nightwave.util.NoraHelper;
import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.voidtrader.ShopReminder;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.util.TimerTask;

@Getter
@Setter
public class NoraStatusSwitcher extends TimerTask {
    private BasicBot bot;

    public NoraStatusSwitcher(BasicBot bot) {
        setBot(bot);
    }

    @Override
    public void run() {
        if(NoraHelper.retrieveActiveState(getBot().getLocale())) {
            getBot().overwriteActivity(Activity.watching("Season " + NoraHelper.retrieveSeasonInfo() + " Phase " + NoraHelper.retrievePhaseInfo()));
            getBot().overwriteOnlineStatus(OnlineStatus.ONLINE);
            getBot().overwriteIdle(false);
        } else {
            getBot().overwriteActivity(Activity.listening("For your dreams"));
            getBot().overwriteOnlineStatus(OnlineStatus.DO_NOT_DISTURB);
            getBot().overwriteIdle(true);
        }
    }
}
