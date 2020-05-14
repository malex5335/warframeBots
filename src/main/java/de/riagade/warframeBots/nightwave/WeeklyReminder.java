package de.riagade.warframeBots.nightwave;

import java.util.TimerTask;

public class WeeklyReminder  extends TimerTask {
    @Override
    public void run() {
        // output that this is a weekly
        // output mission name
        // output 'elite' if name contains 'WeeklyHard'
        System.out.println("I'm a Weekly!");
    }

}
