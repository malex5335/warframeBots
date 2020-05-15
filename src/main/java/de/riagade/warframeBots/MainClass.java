package de.riagade.warframeBots;

import de.riagade.warframeBots.nightwave.NoraNight;
import de.riagade.warframeBots.voidtrader.BaroKiTeer;

public class MainClass {
    public static void main(String[] args) {
        NoraNight noraNight = new NoraNight();
        noraNight.connect();
        noraNight.setUpTasks();

        BaroKiTeer baroKiTeer = new BaroKiTeer();
        baroKiTeer.connect();
        baroKiTeer.setUpTasks();
    }
}