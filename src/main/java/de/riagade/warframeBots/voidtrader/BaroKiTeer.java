package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.util.BasicBot;
import de.riagade.warframeBots.util.CronHelper;
import net.dv8tion.jda.api.entities.Activity;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaroKiTeer extends BasicBot {
    public static final String TOKEN = System.getenv().get("BARO_TOKEN");
    public static final long GUILD_ID = 564803688301068288L;
    public static final long CHANNEL_ID = 571408897420820513L;
    public static final Locale LOCALE = Locale.UK;

    public BaroKiTeer() {
        super(BaroKiTeer.TOKEN, BaroKiTeer.GUILD_ID, BaroKiTeer.CHANNEL_ID, BaroKiTeer.LOCALE);
    }

    public static void injectInstance() {
        BaroKiTeer baroKiTeer = new BaroKiTeer();
        baroKiTeer.connect();
        baroKiTeer.setUpTasks();
    }

    public void setUpTasks() {
        new Timer().scheduleAtFixedRate(
                new ShopReminder(this),
                CronHelper.getNextDate("0 0 14 ? * FRI *"),
                TimeUnit.DAYS.toMillis(7));
        new Timer().scheduleAtFixedRate(
                new PastItemMessagesCorrector(this),
                CronHelper.getNextDate("0 0 * ? * * *"),
                TimeUnit.HOURS.toMillis(1));
        new Timer().scheduleAtFixedRate(
                new BaroStatusSwitcher(this),
                CronHelper.getNextDate("0 * * ? * * *"),
                TimeUnit.MINUTES.toMillis(30));
    }

}
