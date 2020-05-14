package de.riagade.warframeBots.nightwave;

import java.util.TimerTask;

public class DailyReminder extends TimerTask {
    @Override
    public void run() {
        // output that this is a daily
        // output mission name
        // output till when it can be done.
        System.out.println("I'm a Daily!");
    }

}