package de.riagade.warframeBots.bots.nightwave;

import de.riagade.genericDiscordBot.A_TimedBot;
import de.riagade.genericDiscordBot.A_TimedTask;
import de.riagade.warframeBots.pastMessageCorrector.NoraPastMessagesCorrectorTask;
import de.riagade.warframeBots.dailyReminder.NoraDailyReminderTask;
import de.riagade.warframeBots.weeklyReminder.NoraWeeklyReminderTask;
import de.riagade.warframeBots.statusSwitcher.NoraStatusSwitcherTask;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class NoraBot extends A_TimedBot {

    public NoraBot(String token, long guildId, long channelId, Locale locale) {
        super(token, guildId, channelId, locale);
    }

    @Override
    public List<A_TimedTask> getTasksList() {
        List<A_TimedTask> tasks = new ArrayList<>();
        tasks.add(new NoraDailyReminderTask(this,"0 0 1 ? * *"));
        tasks.add(new NoraWeeklyReminderTask(this,"5 0 1 ? * 2/7"));
        tasks.add(new NoraStatusSwitcherTask(this,"0 * * ? * *"));
        tasks.add(new NoraPastMessagesCorrectorTask(this,"0 * * ? * *"));
        return tasks;
    }
}
