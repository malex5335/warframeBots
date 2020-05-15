package de.riagade.warframeBots.nightwave;

import de.riagade.warframeBots.BasicBot;

import java.util.Calendar;
import java.util.Date;
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
        new Timer().scheduleAtFixedRate(
                new DailyReminder(this),
                getNextStart(3),
                TimeUnit.DAYS.toMillis(1));
        new Timer().scheduleAtFixedRate(
                new WeeklyReminder(this),
                getNextStartWeekly(3),
                TimeUnit.DAYS.toMillis(7));
    }

    public static Date getNextStart(int hourOfDay){
        Calendar nextStart = Calendar.getInstance();
        if (nextStart.get(Calendar.HOUR_OF_DAY) >= hourOfDay) {
            nextStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        nextStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        nextStart.set(Calendar.MINUTE, 0);
        nextStart.set(Calendar.SECOND, 0);
        nextStart.set(Calendar.MILLISECOND, 0);
        return nextStart.getTime();
    }

    public static Date getNextStartWeekly(int hourOfDay){
        Calendar nextStart = Calendar.getInstance();
        if (!(nextStart.get(Calendar.HOUR_OF_DAY) < hourOfDay
                && nextStart.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
            nextStart.add(Calendar.WEEK_OF_YEAR, 1);
            nextStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        nextStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        nextStart.set(Calendar.MINUTE, 0);
        nextStart.set(Calendar.SECOND, 0);
        nextStart.set(Calendar.MILLISECOND, 0);
        return nextStart.getTime();
    }

}