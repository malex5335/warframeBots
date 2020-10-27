package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.voidtrader.util.BaroHelper;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.util.TimerTask;

@Getter
@Setter
public class BaroStatusSwitcher extends TimerTask {
    private BasicBot bot;

    public BaroStatusSwitcher(BasicBot bot) {
        setBot(bot);
    }

    @Override
    public void run() {
        if(BaroHelper.retrieveActiveState()) {
            getBot().overwriteActivity(Activity.playing("at " + BaroHelper.retrieveShopLocation()));
            getBot().overwriteOnlineStatus(OnlineStatus.ONLINE);
            getBot().overwriteIdle(false);
        } else {
            getBot().overwriteActivity(Activity.playing("Hide and Seek"));
            getBot().overwriteOnlineStatus(OnlineStatus.DO_NOT_DISTURB);
            getBot().overwriteIdle(true);
        }
    }
}
