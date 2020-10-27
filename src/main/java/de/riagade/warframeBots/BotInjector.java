package de.riagade.warframeBots;

import de.riagade.warframeBots.nightwave.NoraNight;
import de.riagade.warframeBots.voidtrader.BaroKiTeer;

public class BotInjector {

    public static void main(String[] args) {
        BaroKiTeer.injectInstance();
        NoraNight.injectInstance();
    }

}