package de.riagade.warframe;

import de.riagade.warframe.voidtrader.BaroKiTeer;

public class App {
    public static void main(String[] args) {
        BaroKiTeer.injectInstance();
        NoraApp.startBot();
    }

}
