package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.BasicBot;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ReadyEvent;

import javax.annotation.Nonnull;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BaroKiTeer extends BasicBot {
    public static final String TOKEN = "NzEwNDMzODk1OTUwMDU3NTU0.Xr0hUg.OR5hNFw4jpL8CODqne-6zvCnToM";
    public static final long GUILD_ID = 564803688301068288L;
    public static final long CHANNEL_ID = 571408897420820513L;

    public BaroKiTeer() {
        super(BaroKiTeer.TOKEN, BaroKiTeer.GUILD_ID, BaroKiTeer.CHANNEL_ID);
    }

    public static void setUpTasks() {
        new Timer().scheduleAtFixedRate(
                new ShopReminder(),
                getNextStart(16),
                TimeUnit.DAYS.toMillis(14));
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        sendMessage("Browsing is always free. How fortunate for you.");
    }

    @Override
    public void onDisconnect(@Nonnull DisconnectEvent event) {
        sendMessage("I'm afraid I must be off; the Void is calling my name.");
    }

}