package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.BasicBot;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BaroKiTeer extends BasicBot {
    public static final String TOKEN = "NzEwNDMzODk1OTUwMDU3NTU0.Xr0hUg.OR5hNFw4jpL8CODqne-6zvCnToM";
    public static final long GUILD_ID = 564803688301068288L;
    public static final long CHANNEL_ID = 571386096051814410L;

    public BaroKiTeer() {
        super(BaroKiTeer.TOKEN, BaroKiTeer.GUILD_ID, BaroKiTeer.CHANNEL_ID);
    }

    public static void setUpTasks() {
        new Timer().scheduleAtFixedRate(
                new ShopReminder(),
                getNextStart(16),
                TimeUnit.DAYS.toMillis(14));
    }

}