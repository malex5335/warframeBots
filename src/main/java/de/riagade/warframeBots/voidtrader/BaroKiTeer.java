package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.util.BasicBot;

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
                getNextStartBiWeekly(null,14, 0, 0),
                TimeUnit.DAYS.toMillis(14));
    }

    public static Date getNextStartBiWeekly(Date date, int hourOfDay, int minute, int second){
        Calendar nextStart = Calendar.getInstance(BaroKiTeer.LOCALE);
        if(date != null) {
            nextStart.setTimeInMillis(date.getTime());
        }
        if(nextStart.get(Calendar.WEEK_OF_YEAR) % 2 == 0){
            // Baro Ki'Teer is not active curing even weeks, therefore he's coming next week
            nextStart.add(Calendar.WEEK_OF_YEAR, 1);
        } else if(nextStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
                && nextStart.get(Calendar.HOUR_OF_DAY) >= hourOfDay
                || isWeekend(nextStart)) {
            // if it's friday or weekend during odd weeks he's already there, therefore the next time is in 2 weeks
            nextStart.add(Calendar.WEEK_OF_YEAR, 2);
        }
        nextStart.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        nextStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        nextStart.set(Calendar.MINUTE, minute);
        nextStart.set(Calendar.SECOND, second);
        nextStart.set(Calendar.MILLISECOND, 0);
        return nextStart.getTime();
    }

    private static boolean isWeekend(Calendar c){
        return c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

}
