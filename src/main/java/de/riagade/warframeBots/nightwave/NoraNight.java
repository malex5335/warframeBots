package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.BasicBot;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ReadyEvent;

import javax.annotation.Nonnull;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class NoraNight extends BasicBot {
    public static final String TOKEN = "NzEwMzk0MTYwMDY2MDY4NTMx.Xr0blg.LiOz86e3J5b-uVbLbY6iMDl-rcE";
    public static final long GUILD_ID = 564803688301068288L;
    public static final long CHANNEL_ID = 571386096051814410L;

    public NoraNight() {
        super(NoraNight.TOKEN, NoraNight.GUILD_ID, NoraNight.CHANNEL_ID);
    }

    public void setUpTasks() {
        sendMessage("It's that time again, Dreamers.");
        new Timer().scheduleAtFixedRate(
                new DailyReminder(),
                getNextStart(3),
                TimeUnit.DAYS.toMillis(1));
        new Timer().scheduleAtFixedRate(
                new WeeklyReminder(),
                getNextStart(3),
                TimeUnit.DAYS.toMillis(7));
    }
}