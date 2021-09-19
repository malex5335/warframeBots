package de.riagade.warframeBots.statusSwitcher;

import de.riagade.genericDiscordBot.A_BasicBot;
import de.riagade.genericDiscordBot.A_TimedTask;
import de.riagade.warframeBots.bots.nightwave.util.NoraUtil;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

@Slf4j
public class NoraStatusSwitcherTask extends A_TimedTask {
    public NoraStatusSwitcherTask(A_BasicBot bot, String cronExpression) {
        super(bot, cronExpression);
    }

    @Override
    public void runTimedLogic() {
        log.debug("status switcher running");
        if(NoraUtil.retrieveActiveState(getBot().getLocale())) {
            getBot().overwriteActivity(
                    Activity.watching("Season " + NoraUtil.retrieveSeasonInfo() + " Episode " + (NoraUtil.retrievePhaseInfo() + 1)));
            getBot().overwriteOnlineStatus(OnlineStatus.ONLINE);
            getBot().overwriteIdle(false);
        } else {
            getBot().overwriteActivity(Activity.listening("For your dreams"));
            getBot().overwriteOnlineStatus(OnlineStatus.DO_NOT_DISTURB);
            getBot().overwriteIdle(true);
        }
    }
}
