package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.CronHelper;
import net.dv8tion.jda.api.entities.Activity;

import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class NoraNight extends BasicBot {
    public static final String TOKEN = System.getenv().get("NORA_TOKEN");
    public static final long GUILD_ID = 564803688301068288L;
    public static final long CHANNEL_ID = 571386096051814410L;
    public static final Locale LOCALE = Locale.UK;

    public NoraNight() {
        super(NoraNight.TOKEN, NoraNight.GUILD_ID, NoraNight.CHANNEL_ID, NoraNight.LOCALE);
    }

    public static void injectInstance() {
        NoraNight noraNight = new NoraNight();
        noraNight.connect();
        noraNight.setUpTasks();
        noraNight.overwriteActivity(Activity.watching("For some lucky Dreamers"));
    }

    public void setUpTasks() {
        new Timer().scheduleAtFixedRate(
                new DailyReminder(this),
                CronHelper.getNextDate("0 0 1 ? * * *"),
                TimeUnit.DAYS.toMillis(1));
        new Timer().scheduleAtFixedRate(
                new WeeklyReminder(this),
                CronHelper.getNextDate("5 0 1 ? * 2/7 *"),
                TimeUnit.DAYS.toMillis(7));
        new Timer().scheduleAtFixedRate(
                new PastChallengeMessagesCorrector(this),
                CronHelper.getNextDate("0 0 * ? * * *"),
                TimeUnit.HOURS.toMillis(1));
        new Timer().scheduleAtFixedRate(
                new NoraStatusSwitcher(this),
                CronHelper.getNextDate("0 * * ? * * *"),
                TimeUnit.MINUTES.toMillis(30));
    }

}
