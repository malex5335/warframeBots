package de.riagade.genericDiscordBot;

import java.util.List;
import java.util.Locale;

/**
 * a timed Discord bot extending the {@link A_BasicBot} for generic
 * and scheduled tasks which connects automatically.<br>
 * schedule tasks using {@link #getTasksList()}.
 */
@SuppressWarnings("unused")
public abstract class A_TimedBot extends A_BasicBot {
    /**
     * @param botToken  the token which the bot uses to authenticate
     * @param guildId   the server id to connect the bot to
     * @param channelId the channel id for the bot to look at
     * @param locale    the locale for the bot to use
     */

    public A_TimedBot(String botToken, long guildId, long channelId, Locale locale) {
        super(botToken, guildId, channelId, locale);
        executeLogic();
    }

    private void executeLogic() {
        if(getTasksList() != null) {
            for (A_TimedTask task : getTasksList()) {
                task.initiate();
            }
        }
    }

    /**
     * all of the {@link A_TimedTask} will be executed.
     * null as a return is allowed.
     */
    public abstract List<A_TimedTask> getTasksList();
}
