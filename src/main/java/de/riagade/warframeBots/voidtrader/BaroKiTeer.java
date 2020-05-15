package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.BasicBot;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BaroKiTeer extends BasicBot {
    public static final String TOKEN = "NzEwNDMzODk1OTUwMDU3NTU0.Xr0hUg.OR5hNFw4jpL8CODqne-6zvCnToM";
    public static final long GUILD_ID = 564803688301068288L;
    public static final long CHANNEL_ID = 571408897420820513L;

    public BaroKiTeer() {
        super(BaroKiTeer.TOKEN, BaroKiTeer.GUILD_ID, BaroKiTeer.CHANNEL_ID);
    }

    public void setUpTasks() {
        new Timer().scheduleAtFixedRate(
                new ShopReminder(),
                getNextStartBiWeekly(16),
                TimeUnit.DAYS.toMillis(14));
    }

    public static Date getNextStartBiWeekly(int hourOfDay){
        Calendar nextStart = Calendar.getInstance();
        if(nextStart.get(Calendar.WEEK_OF_YEAR) % 2 == 0){
            if(nextStart.get(Calendar.HOUR_OF_DAY) >= hourOfDay
                    && nextStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                nextStart.add(Calendar.WEEK_OF_YEAR, 2);
            } else if(nextStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                nextStart.add(Calendar.WEEK_OF_YEAR, 1);
            }
        } else if(nextStart.get(Calendar.HOUR_OF_DAY) >= hourOfDay
                && nextStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            nextStart.add(Calendar.WEEK_OF_YEAR, 1);
        }
        if (!(nextStart.get(Calendar.HOUR_OF_DAY) < hourOfDay
                && nextStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)) {
            nextStart.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        }
        nextStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        nextStart.set(Calendar.MINUTE, 0);
        nextStart.set(Calendar.SECOND, 0);
        nextStart.set(Calendar.MILLISECOND, 0);
        return nextStart.getTime();
    }

}