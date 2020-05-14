package de.riagade.warframeBots.nightwave;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CephaliteReminder extends TimerTask {
    private int skipEvery;
    private int skipIn;

    public CephaliteReminder(int skipEvery){
        setSkipEvery(skipEvery);
        setSkipIn(skipEvery);
    }

    private int getSkipIn() {
        return skipIn;
    }

    private void setSkipIn(int skipIn) {
        this.skipIn = skipIn;
    }

    public int getSkipEvery() {
        return skipEvery;
    }

    private void setSkipEvery(int skipEvery) {
        this.skipEvery = skipEvery;
    }

    @Override
    public void run() {
        setSkipIn(getSkipIn()-1);
        if(getSkipIn() > 0){
            // output that people can drop 'Cephalite Resonance'
            System.out.println("yeah I'm in!");
        } else {
            // output nothing
            setSkipIn(getSkipEvery());
            System.out.println("not letting you pass!");
        }
    }

    public static Date getMyNextStartDate(int minuteInterval){
        Calendar nextStart = Calendar.getInstance();
        nextStart.set(Calendar.MILLISECOND, 0);
        nextStart.set(Calendar.SECOND, 0);
        while(nextStart.get(Calendar.MINUTE) % minuteInterval > 0){
            System.out.println("adding 1 minute");
            nextStart.add(Calendar.MINUTE, 1);
        }
        System.out.println(nextStart.getTimeInMillis());

        return nextStart.getTime();
    }

}