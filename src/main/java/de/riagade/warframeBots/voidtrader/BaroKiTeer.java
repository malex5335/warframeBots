package de.riagade.warframeBots.voidtrader;

import de.riagade.warframeBots.util.BasicBot;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BaroKiTeer extends BasicBot {
    public static final String TOKEN = "NzEwNDMzODk1OTUwMDU3NTU0.Xr0hUg.OR5hNFw4jpL8CODqne-6zvCnToM";
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
                new ShopReminder(),
                getNextStartBiWeekly(16, 0, 0),
                TimeUnit.DAYS.toMillis(14));
    }

    public static Date getNextStartBiWeekly(int hourOfDay, int minute, int second){
        Calendar nextStart = Calendar.getInstance(BaroKiTeer.LOCALE);
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
        nextStart.set(Calendar.MINUTE, minute);
        nextStart.set(Calendar.SECOND, second);
        nextStart.set(Calendar.MILLISECOND, 0);
        return nextStart.getTime();
    }

}