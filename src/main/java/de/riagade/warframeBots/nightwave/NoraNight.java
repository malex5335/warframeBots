package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.util.BasicBot;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class NoraNight extends BasicBot {
    public static final String TOKEN = "NzEwMzk0MTYwMDY2MDY4NTMx.Xr0blg.LiOz86e3J5b-uVbLbY6iMDl-rcE";
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
    }

    public void setUpTasks() {
        new Timer().scheduleAtFixedRate(
                new DailyReminder(this),
                getNextStart(3, 0, 0),
                TimeUnit.DAYS.toMillis(1));
        new Timer().scheduleAtFixedRate(
                new WeeklyReminder(this),
                getNextStartWeekly(3, 0, 5),
                TimeUnit.DAYS.toMillis(7));
    }

    public static Date getNextStart(int hourOfDay, int minute, int second){
        Calendar nextStart = Calendar.getInstance(NoraNight.LOCALE);
        if (nextStart.get(Calendar.HOUR_OF_DAY) >= hourOfDay) {
            nextStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        nextStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        nextStart.set(Calendar.MINUTE, minute);
        nextStart.set(Calendar.SECOND, second);
        nextStart.set(Calendar.MILLISECOND, 0);
        return nextStart.getTime();
    }

    public static Date getNextStartWeekly(int hourOfDay, int minute, int second){
        Calendar nextStart = Calendar.getInstance(NoraNight.LOCALE);
        if (!(nextStart.get(Calendar.HOUR_OF_DAY) < hourOfDay
                && nextStart.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            nextStart.add(Calendar.WEEK_OF_YEAR, 1);
            nextStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        nextStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        nextStart.set(Calendar.MINUTE, minute);
        nextStart.set(Calendar.SECOND, second);
        nextStart.set(Calendar.MILLISECOND, 0);
        return nextStart.getTime();
    }

}